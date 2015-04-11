class School:
    def __init__(self, name):
        self.db = {}

    def add(self, student, grade):
        if not grade in self.db:
            self.db[grade] = {student}
        else:
            self.db[grade].add(student)

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        else:
            return set()
    
    def sort(self):
        return [(grade,tuple(student for student in self.db[grade])) for grade in self.db.keys() ]
