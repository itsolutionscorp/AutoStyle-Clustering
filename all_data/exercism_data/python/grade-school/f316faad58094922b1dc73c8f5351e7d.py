class School:

    def __init__(self, name='Learn at home'):
        self._db = {}
        self._name = name

    def add(self, who, grade):
        if grade not in self._db:
            self._db[grade] = set()
        self._db[grade].add(who)

    def grade(self, grade):
        return self._db.get(grade, set())

    def sort(self):
        for grade in self._db:
            self._db[grade] = tuple(name for name in sorted(self._db[grade]))
        return self._db

    @property
    def db(self):
        return self._db
