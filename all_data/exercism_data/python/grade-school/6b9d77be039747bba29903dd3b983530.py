class School:
    def __init__(self, name):
        self.db = {}
        
    def add(self, student, grade):
        students = self.db.setdefault(grade, set())
        students.add(student)

    def grade(self, grade):
        return self.db.get(grade, set())
    
    def sort(self):
        return ((grade, tuple(sorted(self.db[grade]))) for grade in sorted(self.db.keys()))
