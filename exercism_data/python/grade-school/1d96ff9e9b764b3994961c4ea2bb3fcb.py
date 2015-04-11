class School:
    def __init__(self, name):
        self.db = {}
        
    def add(self, name, grade):
        try:
            self.db[grade].add(name)
        except KeyError:
            self.db[grade] = set([name])
            
    def grade(self, grade):
        return self.db[grade] if grade in self.db.keys() else set()
        
    def sort(self):
        return {key: tuple(sorted(list(val_set))) for key, val_set in self.db.items()}
        
