from collections import OrderedDict

class School(object):
    def __init__(self, schoolname):
        self.name = schoolname
        self.db = {}
        
    def add(self, child, grade):
        if grade in self.db:
            self.db[grade].update([child])
        else:
            self.db[grade] = set([child])  

    def grade(self,grade):
        if grade in self.db:
            return self.db[grade]
        else:
            return set()

    #this function must return tuples instead of
    #sets, for some strange reason....?
    #used OrderedDict for this purpose
    def sort(self):
        ordered_db = OrderedDict()
        for grade in sorted(self.db):
            ordered_db[grade] = tuple(sorted(self.db[grade]))
        return ordered_db
            
