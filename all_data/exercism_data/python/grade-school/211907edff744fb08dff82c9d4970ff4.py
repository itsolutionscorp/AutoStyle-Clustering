class School(object):
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = dict()
        
    def grade(self, grade):
        if self.db.has_key(grade):
            return self.db[grade]
        else:
            return set()

    def add(self, name, grade):
        names = self.grade(grade)
        names.add(name)
        self.db[grade] = names
    
    def sort(self):
        return {key: tuple([name for name in self.db[key]]) for key in self.db.keys()}
