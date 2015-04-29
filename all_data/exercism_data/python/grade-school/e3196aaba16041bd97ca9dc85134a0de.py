class School(object):
    def __init__(self, school):
        self.school = school
        self.db = {}

    def add(self, name, studentgrade):
        if studentgrade not in self.db:
            self.db[studentgrade] = {name}
        else:
            self.db[studentgrade].add(name)
    def grade(self, grade):
        if grade not in self.db:
            self.db[grade] = set()
        return self.db[grade]

    def sort(self):
        for i in self.db:
            self.db[i] = sorted(self.db[i])
        return self.db
