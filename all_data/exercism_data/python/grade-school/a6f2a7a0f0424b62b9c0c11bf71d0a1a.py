class School():

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, grade):
        if grade in self.db:
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}

    def grade(self, num):
        if num in self.db:
            return self.db[num]
        else:
            return set()
    
    def sort(self):
        students = {}
        for grade in self.db:
            students[grade] = tuple(sorted(self.db[grade]))
        return students
