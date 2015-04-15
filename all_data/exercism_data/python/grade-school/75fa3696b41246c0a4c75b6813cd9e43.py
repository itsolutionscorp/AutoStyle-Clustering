class School:
    def __init__(self, name):
        self.db = {}
        self.name = name
        
    def add(self, student, gradelevel):
        if not gradelevel in self.db:           # grade level not yet added
            self.db[gradelevel] = {student}
        else:
            self.db[gradelevel] |= {student}
            
    def db(self):
        return self.db
    
    def grade(self, gradelevel):
        if gradelevel in self.db:
            return self.db[gradelevel]
        else:
            return set()
    
    def sort(self):
        sorteddb = {}
        
        for i in self.db:
            sorteddb[i] = tuple(sorted(list(self.db[i])))
            
        return sorteddb
