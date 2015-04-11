from collections import defaultdict


class School:
    _db = defaultdict(set)

    def __init__(self, name):
        self.name = name

    def add(self, name, grade):
        self._db[grade].add(name)

    @property
    def db(self):
        db = dict(self._db)
        self._db.clear()
        return db
