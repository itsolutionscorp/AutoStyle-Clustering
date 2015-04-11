# William Morris
# exercism.io
# school.py

class School:

    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def int_guardian(self, test, low=1, high=12):
        if not isinstance(test, int):
            raise TypeError('For grade, expected int type, got ' + str(type(test)))
        elif test<low or test>high:
            raise ValueError('Grade value not between ' + str(low) +' and ' + str(high))
        else:
            return test

    def str_guardian(self, test):
        if not isinstance(test, str):
            raise TypeError('For name, expected string type, got ' + str(type(test)))
        elif not test.isalpha():
            raise ValueError('Special characters not allowed in name')
        else:
            return test

    def add(self, student, grade):
        grade = self.int_guardian(grade)
        student = self.str_guardian(student)
        self.db[grade] = self.db.get(grade,set())
        self.db[grade].add(student)
        return None

    def grade(self, grade_level):
        return self.db.get(grade_level,set())

    def sort(self):
        return {grade:tuple(sorted(self.db[grade])) for grade in self.db}
    
     
