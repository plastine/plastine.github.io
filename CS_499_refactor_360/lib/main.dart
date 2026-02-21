import 'dart:io';
import 'package:flutter/material.dart';
import 'package:permission_handler/permission_handler.dart';
import 'screens/inventory_screen.dart';
import 'screens/login_screen.dart';
import 'package:flutter_application_cs_499/db/database_login';

//main function that is used for running the application
void main() {
  runApp(const MyApp());
}
//my app runs as a stateless widget
class MyApp extends StatelessWidget {
  const MyApp({super.key});
//build method which is used to create the widget tree
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'CS 360',
      //disables the debug banner
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const LoginScreen(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool _hasSmsPermission = false;

  @override
  void initState() {
    super.initState();
    _checkSmsPermission();
  }

  Future<void> _checkSmsPermission() async {
    // iOS: no direct SEND_SMS permission / capability like Android
    if (Platform.isIOS) {
      _show("iOS prevents directly sending SMS.");
      setState(() => _hasSmsPermission = false);
      return;
    }

    final status = await Permission.sms.status;

    if (status.isGranted) {
      _accessApp();
      return;
    }

    final result = await Permission.sms.request();

    if (result.isGranted) {
      _accessApp();
    } else {
      _show("ERROR: Permission not granted");
      setState(() => _hasSmsPermission = false);
    }
  }

  void _accessApp() {
    setState(() => _hasSmsPermission = true);
    _show("Permission granted. App is now accessible!");
  }

  void _show(String msg) {
    if (!mounted) return;
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(msg)),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          mainAxisAlignment: MainAxisAlignment.center,
          children: _hasSmsPermission
              ? [
                  const Text(
                    "Permissions granted.\nApp content goes here.",
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(height: 16),
                  ElevatedButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (_) => const InventoryScreen()),
                        );
                    },
                    child: const Text("Continue"),
                  ),
                ]
              : [
                  const Text(
                    "SMS permission required to continue.",
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(height: 16),
                  ElevatedButton(
                    onPressed: _checkSmsPermission,
                    child: const Text("Grant Permission"),
                  ),
                ],
        ),
      ),
    );
  }
}