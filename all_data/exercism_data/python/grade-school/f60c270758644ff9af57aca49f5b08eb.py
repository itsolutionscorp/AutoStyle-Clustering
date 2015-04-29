class School(object):
    def __init__(self, school_name):
        self.school_name = school_name
        self._db = {}

    @property
    def db(self):
        return self._db

    def add(self, student, grade):
        if grade not in self._db:
            self._db[grade] = set()
        self._db[grade].add(student)

    def grade(self, grade):
        return self._db.get(grade, set())

    def sort(self):
        return { grade: tuple(sorted(students)) for grade, students in self._db.iteritems() }
