class School:
    def __init__(self, name):
        self._db = {}

    def add(self, name, grade):
        self._db.setdefault(grade, set()).add(name)

    def grade(self, n):	
        return self._db.get(n, set())

    def sort(self):
        return [ (v,tuple(self._db[v])) for v in sorted(self._db) ]

	
    @property
    def db(self):
        return self._db
