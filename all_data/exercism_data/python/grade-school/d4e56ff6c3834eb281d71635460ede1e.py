class School:
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def add(self, student, grade):
        if grade in self.db.keys():
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def grade(self, level):
        if level not in self.db:
            return set()
        return self.db[level]

    def sort(self):
        sorted_school = {}
        sorted_grades = sorted(self.db.keys())
        for grade in sorted_grades:
            sorted_school[grade] = tuple(sorted((self.db[grade])))
        return sorted_school
