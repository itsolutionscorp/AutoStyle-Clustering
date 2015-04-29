class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}
    def add(self, student, grade):
        if self.db.has_key(grade):
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}
    def grade(self, grade):
        if self.db.has_key(grade):
            return self.db[grade]
        else:
            return set()
    def sort(self):
        return [tuple([grade, tuple(self.db[grade])]) for grade in self.db.keys()]
