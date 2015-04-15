
class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def grade(self, grade):
        return self.db.get(grade, set())
    
    def sort(self):
        return {grade: tuple(sorted(students))
                for grade, students in self.db.items()}
