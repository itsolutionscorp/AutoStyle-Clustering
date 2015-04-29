class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, grade):
        if grade in self.db:
            self.db[grade].add(name)
        else:
            self.db[grade] = set()
            self.db[grade].add(name)

    def grade(self, number):
        return self.db[number] if number in self.db else set()

    def sort(self):
        # return self.db.items()
        [(grade, tuple(students)) for grade, students in self.db.items()]
