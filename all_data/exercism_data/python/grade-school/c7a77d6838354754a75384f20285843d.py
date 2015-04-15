class School(object):

    def __init__(self, name):
        self.db = {}
        self.name = name

    def grade(self, grade_number):
        if grade_number in self.db:
            return self.db[grade_number]
        else:
            return set()

    def add(self, student, grade_number):
        if grade_number in self.db:
            s = {current_students for current_students in self.db[grade_number]}
            s.add(student)
            self.db.update({grade_number: s})
        else:
            self.db.update({grade_number: {student}})

    def sort(self):
        return [(key, tuple(sorted(value))) for key, value in sorted(self.db.items())]
