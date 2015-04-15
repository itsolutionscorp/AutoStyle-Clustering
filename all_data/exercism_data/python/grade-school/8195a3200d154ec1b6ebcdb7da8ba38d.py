from collections import defaultdict

class School(object):
    
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = defaultdict(set)
    
    def add(self, name, grade):
        self.db[grade].add(name)
        
    def grade(self, grade):
        return self.db[grade]
            
    def sort(self):
        return {i: tuple(sorted(self.db[i]))
                for i in self.db}
