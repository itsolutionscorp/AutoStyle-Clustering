class School():
    def __init__(self, name):
        self.name = name
        self.db = {}
        
    
    def add(self, student_name, grade):
        if not self.db.has_key(grade): self.db[grade] = set()
        self.db[grade].add(student_name)
        
        
    def grade(self, n):
        if not self.db.has_key(n): return set()
        return self.db[n]
        
        
    def sort(self):
        sorted = {}
        for g in self.db.keys():
            sorted[g] = list(self.db[g])
            sorted[g].sort()
            sorted[g] = tuple(sorted[g])
        return sorted
