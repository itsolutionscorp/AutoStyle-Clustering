class School():
    def __init__(self, sname):
        self.schoolname = sname
        self.db = {}

    def add(self, name, grade):
        if self.db.has_key(grade):
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}

    def grade(self, gd):
        if self.db.has_key(gd):
            return self.db[gd]
        return set()

    def sort(self):
        ret = []
        for key in self.db.keys():
            ret.append((key, tuple(self.db[key])))
        ret.sort()
        return ret
