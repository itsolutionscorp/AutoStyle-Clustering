class School(object):
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def add(self, student, grade_level):
        if grade_level in self.db.keys():
            if student not in self.db[grade_level]:
                self.db[grade_level].update({student})
        else:
            self.db[grade_level] = {student}

    def grade(self, grade_level):
        return self.db[grade_level] if grade_level in self.db.keys() else set ()

    def sort(self):
        return [ (key, tuple(self.db[key])) for key in self.db.keys() ]
