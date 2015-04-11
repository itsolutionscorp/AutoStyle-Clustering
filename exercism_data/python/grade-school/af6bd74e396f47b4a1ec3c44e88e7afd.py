class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}
        
    def add(self, student, grade):
        if grade not in self.db:
            self.db[grade] = set()
        self.db[grade].add(student)
        
    def grade(self, grade):
        return self.db.get(grade, set())
    
    def sort(self):
        return [(grade, tuple(students)) for grade, students in self.db.iteritems()]
