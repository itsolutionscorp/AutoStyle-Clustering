class School(object):
    def __init__(self, school_name):
        self.name = school_name
        self.db = {}

    def add(self, name, grade):
        if grade in self.db:
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}

    def grade(self, grade):
        if grade not in self.db:
            return set()
        else:
            return self.db[grade]

    def sort(self):
        students = []
        for grade in self.db:
            students.append( (grade, tuple(sorted(self.db[grade]))) )
        return sorted(students)
