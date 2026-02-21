# Example Python Code to Insert a Document 

from pymongo import MongoClient 
from bson.objectid import ObjectId

class AnimalShelter(object): 
    """ CRUD operations for Animal collection in MongoDB """ 

    def __init__(self,USER,PASS,HOST,PORT,DB,COL): 
        # Initializing the MongoClient. This helps to access the MongoDB 
        # databases and collections. This is hard-wired to use the aac 
        # database, the animals collection, and the aac user. 
        # 
        # You must edit the password below for your environment. 
        # 
        # Connection Variables 
        # 
        #USER = 'aacuser' 
        #PASS = 'SNHU1234' 
        #HOST = 'localhost' 
        #PORT = 27017 
        #DB = 'AAC'
       # COL = 'animals' 
        # 
        # Initialize Connection 
        # 
        self.client = MongoClient('mongodb://%s:%s@%s:%d' % (USER,PASS,HOST,PORT)) 
        self.database = self.client['%s' % (DB)] 
        self.collection = self.database['%s' % (COL)] 
        

    # Create a method to return the next available record number for use in the create method
    
    # Complete this create method to implement the C in CRUD. 
    def create(self, data):
        if data is not None: #cannot insert a null document
            try:
                self.collection.insert_one(data)  #inserts data into document
                return True #needs to return true for testing purposes
            except Exception as e:
                print(f"Failed to create: {e}")        
        else: 
            raise Exception("Nothing to save, because data parameter is empty") #to prevent entering empty data sets
            return False #returns false to show create method did not work correctly

    # Create method to implement the R in CRUD.
    def read(self, query):
        if query is not None:
            try:
                cursor = self.collection.find(query)
                return list(cursor) #returns cursor as a list
            except Exception as e:
                print(f"Failed to read: {e}")
                raise []
        else: 
            return [] #returns empty list if read did not work correctly
        
    def update_one(self,data,new_values):
        if data is not None:
            try:
                original_record = self.collection.find_one(data)
                if not original_record:
                    print("No records were found, so none were modified")
                    return False

                result = self.collection.update_one(data, {'$set': new_values})
                return(f"Total updates: {result.modified_count}")  
        
            except Exception as e:
                print(f"failed to update: {e}")
        else: 
            raise Exception("failed to update: No data has been selected for updating")
    def update_many(self, data, new_values):
        if data is not None:
            try:
                original_record = self.collection.find(data)
                if not original_record:
                    print("No records were found, so none were modified")
                    return False
                
                result =  self.collection.update_many(data, {'$set': new_values})
                return(f"Total updates: {result.modified_count}")  
        
            except Exception as e:
                print(f"failed to update: {e}")
        else: 
            raise Exception("failed to update: No data has been selected for updating")
                
            
    def delete(self, result):
        if result is not None:
            try:
                original_record = self.collection.find(result)
                if not original_record:
                    print("No records were found, so none were deleted")
                result =self.collection.delete_one(result)
                if result.deleted_count > 0:
                    return(f"Records Deleted: {result.deleted_count}")
            except Exception as e:
                print(f"Failed to delete: {e}")
        else: 
            raise Exception("failed to delete: No records selected for deletion")
            return False
                