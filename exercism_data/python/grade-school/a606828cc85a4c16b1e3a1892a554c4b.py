class School(object):

    def __init__(self, name):
        self.school_name = name
        self.db = dict()

    def add(self, name, grade):
        if not grade in self.db:
            self.db[grade] = set()
        self.db[grade].add(name)

    def grade(self, num):
        if num in self.db:
            return self.db[num]
        return set()

    def sort(self):
        ret = {}
        for key in sorted(self.db):
            ret[key] = tuple(name for name in sorted(self.db[key]))
        return ret
