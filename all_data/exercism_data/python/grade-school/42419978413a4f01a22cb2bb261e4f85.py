class School:

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        if grade not in self.db:
            self.db[grade] = set([])
        self.db[grade].add(student)

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        else:
            return set([])

    def sort(self):
        res = {}
        for item in self.db:
            res[item] = tuple(sorted(list(self.db[item])))
        return res
        
