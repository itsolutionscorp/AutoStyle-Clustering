from collections import defaultdict

class School(object):
    def __init__(self, school):
        self.school = school
        self.db = defaultdict(set)

    def add(self, name, studentgrade):
        self.db[studentgrade].add(name)
            
    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return {key: tuple(sorted(student)) for key, student in self.db.items()}
