from collections import OrderedDict

class School:
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def __str__(self):
        return(str(self.db))

    def add(self, name, grade):
        if self.db.get(grade):
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}


    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return {key: tuple(sorted(self.db.get(key))) for key in self.db}



if __name__ == '__main__':
    new = School("Test School")
    new.add('First', 2)
    new.add('Second', 2)
    new.add('FirstThird', 3)
    print(new)
