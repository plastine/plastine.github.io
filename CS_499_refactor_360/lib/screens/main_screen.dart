import 'package:flutter/material.dart';

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key});

  Widget _mainHeaderRow() {
    return Container(
      width: double.infinity,
      color: const Color(0xFEF8267E), // pink bar from XML
      padding: const EdgeInsets.all(10),
      child: Row(
        children: const [
          Expanded(
            flex: 3,
            child: Padding(
              padding: EdgeInsets.all(10),
              child: Text(
                "Product:",
                style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),
          Expanded(
            flex: 3,
            child: Padding(
              padding: EdgeInsets.all(10),
              child: Text(
                "Amount:",
                style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),
          Expanded(
            flex: 3,
            child: Padding(
              padding: EdgeInsets.all(10),
              child: Text(
                "Delete:",
                style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xC7E2BEBE), // background from XML
      appBar: AppBar(
        title: const Text('Main Screen for rewrite'),
      ),
      body: Column(
        children: [
          _mainHeaderRow(),

          // This is where your RecyclerView/ListView content will go:
          const Expanded(
            child: Center(
              child: Text(
                "Inventory list goes here",
                style: TextStyle(fontSize: 18),
              ),
            ),
          ),
        ],
      ),
    );
  }
}