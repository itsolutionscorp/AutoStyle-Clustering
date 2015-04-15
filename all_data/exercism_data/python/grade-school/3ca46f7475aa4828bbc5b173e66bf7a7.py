class School:
    def __init__(self,title):
        self.title = title
        self.db = dict()
    def checkIfEmpty(self,grade):
        try:
            if (self.db[grade]):
                return False
        except:
            return True
    def add(self,name,grade):
        if not self.checkIfEmpty(grade):
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}
    def grade(self,num):
        if self.checkIfEmpty(num):
            return set()
        else:
            return self.db[num]
    def sort(self):
        sortdb = {}
        for key in self.db.keys():
            sortdb[key] = tuple(sorted(self.db[key]))
        return sortdb
