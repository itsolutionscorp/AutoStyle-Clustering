class School(object):

    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def add(self, student, grade):
        if not grade in self.db:
            self.db[grade] = {student}
        else:
            self.db[grade].add(student)

    def grade(self, grade):
        if not grade in self.db:
            return set()
        else:
            return self.db[grade]

    def sort(self):
        new_dict = {}
        sorted_keys = sorted(self.db.keys())
        for i in sorted_keys:
            sorted_tuple = tuple(sorted(self.db[i]))
            new_dict[i] = sorted_tuple
        return new_dict
