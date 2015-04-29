__author__ = 'tracyrohlin'

class School:
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def add(self, name, grade):
        if grade in self.db:
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}
        return self.db

    def grade(self, grade_number):
        if grade_number in self.db:
            return self.db[grade_number]
        else:
            return set()

    def sort(self):
        new_dict = {}
        for grade in self.db:

            new_dict[grade] = tuple(sorted(self.db[grade]))

        return new_dict
