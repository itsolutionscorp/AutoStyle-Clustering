class School():
    def __init__(self, name):
        self._db = {}
        self.name = name

    @property
    def db(self):
        return self._db

    def add(self, student, grade):
        if not grade in self._db:
            self._db[grade] = set()
        self._db[grade].add(student)

    def grade(self, grade):
        return self._db.get(grade, set())

    def sort(self):
        return dict((grade, tuple(sorted(students))) for (grade, students) in self._db.items())
