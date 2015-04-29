# Grade School

class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}
        
    def add(self, student_name, grade):
        if grade not in self.db:
            self.db[grade] = set()
            
        self.db[grade].add(student_name)
    
    def grade(self, grade):
        if grade not in self.db:
            return set()
        
        else:   
            return self.db[grade]
    
    def sort(self):
        results = []
        
        grades = self.db.keys()
        
        for grade in grades:
            results.append((grade, tuple(sorted(self.db[grade]))))
            
        return results
