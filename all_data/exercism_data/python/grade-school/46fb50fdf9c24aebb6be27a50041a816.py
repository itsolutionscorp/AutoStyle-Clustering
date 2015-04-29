'''exer school'''

class School:
    '''store students in their respective grades'''

    def __init__(self, school_name):
        '''prep the instance name and db'''

        self.school_name = school_name
        self._db = {}

    def add(self, student, grade):
        '''insert a student into a grade'''

        class_roster = list(self._db.get(grade, []))
        class_roster.append(student)
        class_roster.sort()
        self._db[grade] = set(class_roster)

    @property
    def db(self):
        '''return a copy of the internal db'''
        return self._db

    def grade(self, grade_number):
        '''return the students in the specified grade'''
        return self._db.get(grade_number, set())

    def sort(self):
        '''return a copy of the db, with values cast to tuples'''
        keys = self._db.keys()
        values = self._db.values()
        return dict(zip(keys, [tuple(v) for v in values]))
