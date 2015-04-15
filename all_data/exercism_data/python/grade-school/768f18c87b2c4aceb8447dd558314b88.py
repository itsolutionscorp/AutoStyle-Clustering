class School(object):

    def __init__(self, name):
        self._name = name
        self._db = {}      

    def add(self, student, grade):
        """Adds a given student to a given grade"""
        if grade in self.db:
            self._db[grade].add(student)
        else:
            self._db[grade] = {student}

    def grade(self, grade):
        """Returns a set of all students in a given grade"""
        if grade in self._db:
            return self._db[grade]
        else:
            return set()

    def sort(self):
        """Returns a dictionary sorted by grade as the keys
        and alphabetical student tuples as the values
        """
        return {key:tuple(sorted(list(value)))
                for key,value in sorted(self._db.items())}

    @property
    def db(self):
        return self._db
