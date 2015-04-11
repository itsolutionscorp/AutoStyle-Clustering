class School:

    def __init__(self, school_name):
        self._school_name = school_name
        self._db = dict()

    def add(self, student, grade_no):
        self._db.setdefault(grade_no, set()).add(student)

    def grade(self, grade_no):
        return self._db.get(grade_no, set())

    def sort(self):
        return [(grade_no, tuple(sorted(self.grade(grade_no))))
                for grade_no
                in sorted(self._db.keys())]

    @property
    def db(self):
        return self._db

    @property
    def school_name(self):
        return self._school_name
