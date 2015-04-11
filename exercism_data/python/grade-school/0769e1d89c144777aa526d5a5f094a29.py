class School(object):
    def __init__(self, schoolname):
        self.db = {}
        self.schoolname = schoolname        
    
    def add(self, name, grade):
        if grade not in self.db:
            self.db[grade] = set()
        self.db[grade].add(name)

    def grade(self, grade):
        return self.db.get(grade, set())


    def sort(self):
        return { k : tuple(sorted(v)) for k, v in sorted(self.db.iteritems()) }
