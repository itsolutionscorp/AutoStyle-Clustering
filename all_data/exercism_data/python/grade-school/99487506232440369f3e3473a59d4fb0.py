""" module school for exercism.io programming training """


class School():
    def __init__(self, name, data=None):
        self.name = name
        self.db = {}

    
    def add(self, name, clas):
        if clas in self.db.keys():
            self.db.get(clas).add(name)
        else:
            self.db.update({clas: {name}})
    
    
    def grade(self, clas):
        if clas in self.db.keys():
            return self.db.get(clas)
        else:
            return set()
        
        
    def sort(self):
        keys = sorted(self.db.keys())
        return [(k, tuple(sorted(self.db[k]))) for k in keys]
