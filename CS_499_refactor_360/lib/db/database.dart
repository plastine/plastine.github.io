import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
//this is the inventory database
//relies on the singleton pattern like the login database portion
class InventoryDatabase {
  static final InventoryDatabase instance = InventoryDatabase._();
  InventoryDatabase._();
//database name and version
  static const _dbName = "inventory_Items.db";
  static const _dbVersion = 1;
//table and column names for the database
  static const table = "item_Name";
  static const columnId = "primary_id";
  static const columnAmount = "item_Quantity";
  static const columnWeight = "item_Weight";
  static const columnDescription = "item_Description";
  static const columnFavorite = "item_Necessary";
//caching
  Database? _database;
//for initialization
  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDb();
    return _database!;
  }
//initialize and open the database
  Future<Database> _initDb() async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, _dbName);

    return openDatabase(
      path,
      version: _dbVersion,
      onCreate: _onCreate,
      onUpgrade: _onUpgrade,
    );
  }
//SQL table creation
  Future<void> _onCreate(Database db, int version) async {
    await db.execute('''
      CREATE TABLE $table (
        $columnId INTEGER PRIMARY KEY AUTOINCREMENT,
        $columnAmount INTEGER,
        $columnWeight TEXT,
        $columnDescription TEXT,
        $columnFavorite TEXT
      )
    ''');
  }
//deletes and recreates the table
  Future<void> _onUpgrade(Database db, int oldVersion, int newVersion) async {
    await db.execute("DROP TABLE IF EXISTS $table");
    await _onCreate(db, newVersion);
  }

  // ---------- CRUD METHODS ----------

  Future<int> insertItem(Map<String, dynamic> item) async {
    final db = await database;
    return await db.insert(table, item);
  }

  Future<List<Map<String, dynamic>>> getAllItems() async {
    final db = await database;
    return await db.query(table);
  }

  Future<int> updateItem(int id, Map<String, dynamic> item) async {
    final db = await database;
    return await db.update(
      table,
      item,
      where: '$columnId = ?',
      whereArgs: [id],
    );
  }

  Future<int> deleteItem(int id) async {
    final db = await database;
    return await db.delete(
      table,
      where: '$columnId = ?',
      whereArgs: [id],
    );
  }
}