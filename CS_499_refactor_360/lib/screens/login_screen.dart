import 'package:flutter/material.dart';
import 'package:flutter_application_cs_499/db/database_login';
import 'inventory_screen.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _userCtrl = TextEditingController();
  final _passCtrl = TextEditingController();
  bool _loading = false;

  @override
  void dispose() {
    _userCtrl.dispose();
    _passCtrl.dispose();
    super.dispose();
  }

  void _show(String msg) {
    if (!mounted) return; 
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(msg)),
    );
  }
//asynchronous to keep the UI responsive
  Future<void> _signIn() async {
    final username = _userCtrl.text.trim();
    final password = _passCtrl.text;
//if one of the fields is left blank
    if (username.isEmpty || password.isEmpty) {
      _show("must use all fields");
      return;
    }
//turns on loading for validation, updates the UI
    setState(() => _loading = true);
    try {
      //database check to match the values entered
      final isValid =
          await DatabaseLogin.instance.checkLoginPass(username, password);
      //IF SUCCESS: this portion handles successful login attempts
      if (isValid) {
        _show("Logged In!");
        if (!mounted) return;
        //The navigator is a widget that is used to  
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (_) => const InventoryScreen()),
        );
      ///IF FAILURE: This section handles an unsuccessful login attempt
      } else {
        _show("Invalid login credentials");
      }
    } catch (e) {
      _show("Login error: $e");
    } finally {
      if (mounted) setState(() => _loading = false);
    }
  }

Future<void> _createAccount() async {
  final username = _userCtrl.text.trim();
  final password = _passCtrl.text.trim();

  if (username.isEmpty || password.isEmpty) {
    _show("must use all fields");
    return;
  }

  setState(() => _loading = true);
  try {
    await DatabaseLogin.instance.createUser(username, password);

    //confirm and let them press Verify
    _show("Account created. You can log in now.");

  
  } catch (e) {
    // Most common error: username already exists (UNIQUE constraint)
    final msg = e.toString().contains("UNIQUE")
        ? "Username already exists"
        : "Create account failed: $e";
    _show(msg);
    //allows for buttons and user interaction again
  } finally {
    if (mounted) setState(() => _loading = false);
  }
}
//USER INTERFACE layout, equivalent to the xml file from the original application
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Login")),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            TextField(//username textfield
              controller: _userCtrl,
              decoration: const InputDecoration(
                labelText: "Username",
                border: OutlineInputBorder(),
              ),
            ),
            const SizedBox(height: 12),
            TextField(//password textfield
              controller: _passCtrl,
              decoration: const InputDecoration(
                labelText: "Password",
                border: OutlineInputBorder(),
              ),
              obscureText: true,
              onSubmitted: (_) => _signIn(),
            ),
            const SizedBox(height: 16),
            Row(
              children: [
                Expanded(//create account button
                  child: OutlinedButton(
                    //conditional button enabling/Disabling
                    onPressed: _loading ? null : _createAccount,
                    child: const Text("Create Account"),
                  ),
                ),
                const SizedBox(width: 12),
                Expanded(//sign-in button
                  child: ElevatedButton(
                     //conditional button enabling/Disabling
                    onPressed: _loading ? null : _signIn,
                    child: Text(_loading ? "Signing in..." : "Verify"),
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}