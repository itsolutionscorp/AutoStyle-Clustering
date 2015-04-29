class School(object):
    def __init__(self, school):
        self.school = school
        self._db = {}

    def add(self, student, grade):
        self._db.setdefault(grade, set()).add(student)

    def grade(self, grd):
        return self._db.get(grd, set())

    def sort(self):
        return {k: tuple(sorted(v)) for k, v in self.db.iteritems()}

    @property
    def db(self):
        return self._db.copy()
