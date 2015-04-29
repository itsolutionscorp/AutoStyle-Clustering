class School(object):
    def __init__(self, arg):
        self.school = arg
        self.db = {}

    def add(self, student, grade):
        self.db[grade] = [].append(student)
