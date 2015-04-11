class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, child, grade):
        self.db[grade] = self.db.get(grade, set())
        self.db[grade].update([child])

    def grade(self, num):
        result = set()

        if num in self.db:
            result = self.db[num]

        return result

    def sort(self):
        result = {}

        for k, v in sorted(self.db.items()):
            result[k] = tuple(v)

        return result
