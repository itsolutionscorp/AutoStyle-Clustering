class School(object):
    def __init__(self, name):
        self.name = name
        self.__db = {}
        self.db = self.__db

    def add(self, name, level):
        if level in self.__db: self.__db[level].add(name)
        else:                  self.__db[level] = set([name])
        self.db = self.__db
        

    def grade(self,n):
        if n not in self.__db: return set()
        else:                  return self.__db[n]

    def sort(self):
        s = {}; grades = [];
        
        for grade in self.__db: grades.append(grade)
        grades.sort()
        
        for grade in grades:
            s[grade] = tuple(sorted(self.__db[grade]))
            
        return s

    
