class School(object):
    def __init__(self, schoolname):
        self._db = {}
        self.schoolname = schoolname        
    
    @property
    def db(self):
        return { g : s.copy() for g, s in self._db.iteritems() }

    def add(self, name, grade):
        names_in_grade = self._db.setdefault(grade, set())
        names_in_grade.add(name)

    def grade(self, grade):
        return self._db.get(grade, set())

    def sort(self):
        return { g : tuple(sorted(s)) for g, s in sorted(self._db.iteritems()) }
