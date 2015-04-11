class School:
    def __init__(self, name):
        self.name = name
        self.db = {}
    
    def add(self, name, grade):
        if self.db.get(grade):
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}
    
    def grade(self, grade):
        if not self.db.get(grade):
            return set()
        return self.db[grade]
    
    def sort(self):
        for grade in self.db.keys():
            self.db[grade] = tuple(sorted(self.db[grade]))
        
        return self.db
