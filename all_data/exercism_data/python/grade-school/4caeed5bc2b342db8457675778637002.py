from collections import defaultdict
class School():
    def __init__(self,what):
        self.db = defaultdict(set)
        self.school_name = what

    def add(self,what,grade):
        self.db[grade].add(what)
        
    def grade(self,what):
        return self.db[what]

    def sort(self):
        #for testing purposes 
        new_dict = {}
        for k,v in self.db.items():
            new_dict[k] = tuple(v)
        return new_dict
