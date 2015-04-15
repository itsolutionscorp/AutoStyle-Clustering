
class School:

    def __init__(self, name):
        self.name = name
        self.db = dict()

    def add(self, student, grade):
        old_students = self.db.get(grade, set())
        old_students.add(student)
        self.db[grade] = old_students

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        sorted_db = dict ()
        for grade in sorted(self.db.keys()):
            sorted_db[grade] = tuple(sorted(self.db[grade]))
        return sorted_db
'''
 A tuple is defined in the same way as a list, except that the whole set 
of elements is enclosed in PARENTHESES instead of square brackets. 
'''


'''
dtest = dict()
print (dtest)
dtest = dict([(1, {'1','2','3'}),(2,{'4','5'})])
dtest.get(1).add ('11')
dtest_add = dtest.get(3, set())
dtest_add.add('16') 
dtest[3] = dtest_add   
print(dtest)
'''

'''

school = School("Haleakala Hippy School")
school.add("James", 2)
school.add("Blair", 2)
school.add("Paul", 2)
print(school.db)

'''
