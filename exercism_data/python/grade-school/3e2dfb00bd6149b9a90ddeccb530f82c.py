class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, grade):
        try:
            self.db[grade].update([name])
        except KeyError:
            self.db[grade] = set()
            self.db[grade].update([name])

    def grade(self, grade):
        try:
            return self.db[grade]
        except KeyError:
            return set()

    def sort(self):
        print sorted(self.db.items())
