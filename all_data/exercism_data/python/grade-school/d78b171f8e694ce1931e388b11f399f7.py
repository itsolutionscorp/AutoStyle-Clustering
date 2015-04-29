class School:
    def __init__(self, name):
        self.name = name
        self.db = {}
    def add(self, student, grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}
    def sort(self):
        return {grade: tuple(sorted(students)) for grade, students in self.db.items()}
    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        else:
            return set([])
