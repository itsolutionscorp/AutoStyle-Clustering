class School(object):
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def add(self, student, grade):
        self.db.setdefault(grade, set()).add(student)

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return [(grade, tuple(sorted(students))) for grade, students in sorted(self.db.items())]
