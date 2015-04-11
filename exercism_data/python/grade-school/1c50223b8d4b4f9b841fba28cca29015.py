from collections import defaultdict
class School:
    def __init__(self, name):
        self.name = name
        self.db = dict()
        
    def add(self, name, grade):
        self.db.setdefault(grade, set()).add(name)

    def grade(self, grade):
        self.list = set()
        for item in self.db.get(grade, set()):
            self.list.add(item)
        return self.list

    def sort(self):
        list = {}
        for key in sorted(self.db):
            lists = []
            for item in sorted(self.db[key]):
                lists.append(item)
            list[key] = tuple(lists)
        return list
    
