__author__ = 'jeffmarkey'


class School:
    def __init__(self, school_name):
        self.__school_name__ = school_name
        self.school = self
        self.school.db = {}

    def add(self,name, grade):
        try:
            (self.school.db[grade]).add(name)
        except:
            self.school.db[grade] = set([name])


    def grade(self, number=''):
        try:
            if number == '':
                return self.school.db
            else:
                return self.school.db[number]
        except:
            return set()


    def sort(self):
        tuple_dictionary = {}
        for line in self.school.db.keys():
            tuple_dictionary[line] = tuple(self.school.db[line])
        return tuple_dictionary
