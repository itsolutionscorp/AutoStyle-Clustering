class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student_name, grade):
        self.db.setdefault(grade, set()).add(student_name)

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return {grade: tuple(sorted(students)) for
                grade,students in self.db.items()}
