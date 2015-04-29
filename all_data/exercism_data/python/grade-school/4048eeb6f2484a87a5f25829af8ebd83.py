class School():

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, grade):
        self.db[grade] = self.db.get(grade,set()).union({name})

    def grade(self, num):
        return self.db.get(num,set())
    
    def sort(self):
        students = {}
        for grade in self.db:
            students[grade] = tuple(sorted(self.db[grade]))
        return students
