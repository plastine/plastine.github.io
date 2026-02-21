import 'package:flutter/material.dart';
import 'package:flutter_application_cs_499/db/database.dart';
import 'new_item_screen.dart';
//This is the inventory screen, a stateful widget which allows for user input. 
class InventoryScreen extends StatefulWidget {
  const InventoryScreen({super.key});

  @override
  State<InventoryScreen> createState() => _InventoryScreenState();
}
//This is list which acts as memory for screen
class _InventoryScreenState extends State<InventoryScreen> {
  List<Map<String, dynamic>> _items = [];
  bool _loading = true;

//like the onCreate function, which sets up the recycler view. 
//This is where data is loaded in. 
  @override
  void initState() {
    super.initState();
    _loadItems();
  }
//This is for the database (SQLite). this function is used to read the database and update UI
  Future<void> _loadItems() async {
    setState(() => _loading = true);
    final data = await InventoryDatabase.instance.getAllItems();
    if (!mounted) return;
    setState(() {
      _items = data;
      _loading = false;
    });
  }
//refreshes the screen after adding a new item with the FAB
  Future<void> _goAddItem() async {
    final result = await Navigator.push(
      context,
      MaterialPageRoute(builder: (_) => const NewItemScreen()),
    );

    if (result != null) {
      await _loadItems();
    }
  }
//removes an item from the database
  Future<void> _deleteItem(int id) async {
    await InventoryDatabase.instance.deleteItem(id);
    await _loadItems();
  }

  @override
  //for building the user interace layout
  Widget build(BuildContext context) {
    const bg = Color(0xFEF8267E); // your XML background

    return Scaffold(
      backgroundColor: bg,
      appBar: AppBar(title: const Text("Inventory")),
      body: _loading
          ? const Center(child: CircularProgressIndicator())
          : _items.isEmpty
              ? const Center(
                  child: Text(
                    "No items yet.\nTap + to add one.",
                    textAlign: TextAlign.center,
                  ),
                )
              : ListView.builder(
                  itemCount: _items.length,
                  itemBuilder: (context, index) {
                    final item = _items[index];
                    final id = item[InventoryDatabase.columnId] as int?;
                    final qty = item[InventoryDatabase.columnAmount];
                    final weight = item[InventoryDatabase.columnWeight];
                    final desc = item[InventoryDatabase.columnDescription];
                    final necessary = item[InventoryDatabase.columnFavorite];

                    return Card(
                      child: ListTile(
                        title: Text(desc?.toString() ?? "Item"),
                        subtitle: Text(
                          "Qty: ${qty ?? "-"} | Weight: ${weight ?? "-"} | Necessary: ${necessary ?? "-"}",
                        ),
                        trailing: id == null
                            ? null
                            : IconButton(
                                icon: const Icon(Icons.delete),
                                onPressed: () => _deleteItem(id),
                              ),
                      ),
                    );
                  },
                ),
      //FAB for adding a new item
      floatingActionButton: FloatingActionButton(
        onPressed: _goAddItem,
        child: const Icon(Icons.add),
      ),
    );
  }
}