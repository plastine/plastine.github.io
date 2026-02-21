import 'package:flutter/material.dart';
import 'package:flutter_application_cs_499/db/database.dart'; // or ../db/database.dart if that’s your filename

//new item screen. This screen exists as the FAB in the inventory screen and allows a user to add more items to the database.
//this is stateful as the text fields change
class NewItemScreen extends StatefulWidget {
  const NewItemScreen({super.key});

  @override
  State<NewItemScreen> createState() => _NewItemScreenState();
}

class _NewItemScreenState extends State<NewItemScreen> {
  //controllers for editing text
  final _nameCtrl = TextEditingController();
  final _inventoryCtrl = TextEditingController();
  bool _saving = false;
//manual cleanup instantiated for preventing memory leaks
  @override
  void dispose() {
    _nameCtrl.dispose();
    _inventoryCtrl.dispose();
    super.dispose();
  }
//displays something to the user
  void _show(String msg) {
    if (!mounted) return;
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(msg)),
    );
  }
//this removes text inside of the fields
  void _undo() {
    _nameCtrl.clear();
    _inventoryCtrl.clear();
  }
//this saves the data and adds it to the batabase
  Future<void> _save() async {
    final name = _nameCtrl.text.trim();
    final qty = int.tryParse(_inventoryCtrl.text.trim());
//cannot have a blank or null field
    if (name.isEmpty || qty == null) {
      _show("Please enter item name and total inventory.");
      return;
    }

    setState(() => _saving = true);
    try {
      //inserts into SQLite
      await InventoryDatabase.instance.insertItem({
        InventoryDatabase.columnDescription: name,
        InventoryDatabase.columnAmount: qty,
        InventoryDatabase.columnWeight: "",
        InventoryDatabase.columnFavorite: "no",
      });

      if (!mounted) return;
      //returns to the inventory screen with successul update
      Navigator.pop(context, true); // tells inventory screen to refresh
    } catch (e) {
      //did not update correctly
      _show("Save failed: $e");
    } finally {
      if (mounted) setState(() => _saving = false);
    }
  }
//user interface layout
  @override
  Widget build(BuildContext context) {
    const screenBg = Color(0xFF8C8C8C); // material_dynamic_neutral70-ish
    const titleBg = Color(0xFF3418BC);

    return Scaffold(
      backgroundColor: screenBg,
      appBar: AppBar(
        elevation: 0,
        backgroundColor: Colors.transparent,
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            // Title bar
            Container(
              width: double.infinity,
              color: titleBg,
              padding: const EdgeInsets.only(left: 70, bottom: 10, top: 10),
              child: const Text(
                "New Inventory Menu:",
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 25,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),

            const SizedBox(height: 10),

            // Name field
            Container(
              color: Colors.white,
              child: TextField(
                controller: _nameCtrl,
                decoration: const InputDecoration(
                  hintText: "Name of new item",
                  border: OutlineInputBorder(),
                ),
              ),
            ),

            const SizedBox(height: 10),

            // Inventory field
            Container(
              color: Colors.white,
              child: TextField(
                controller: _inventoryCtrl,
                keyboardType: TextInputType.number,
                decoration: const InputDecoration(
                  hintText: "Total Inventory",
                  border: OutlineInputBorder(),
                ),
              ),
            ),

            const SizedBox(height: 10),

            // Buttons aligned end
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                ElevatedButton(
                  onPressed: _saving ? null : _undo,
                  style: ElevatedButton.styleFrom(
                    backgroundColor: const Color(0xFFE91E63),
                  ),
                  child: const Text("Undo"),
                ),
                const SizedBox(width: 30),
                ElevatedButton(
                  onPressed: _saving ? null : _save,
                  style: ElevatedButton.styleFrom(
                    backgroundColor: const Color(0xFF009688),
                  ),
                  child: Text(_saving ? "Saving..." : "Save"),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}