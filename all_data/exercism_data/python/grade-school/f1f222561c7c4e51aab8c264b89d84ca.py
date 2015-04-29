'''
 ____________________________________ 
< nothing added yet for bonus points >
 ------------------------------------ 
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||                '''
class School:
    def __init__(self, name=None):
        self.name = name
        self.db = {}
    def add(self, student, grade):
        if grade in self.db.keys():
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}
    def grade(self, n):
        try:
            return(self.db[n])
        except:
            return(set())
    def sort(self):
        return({g:tuple(sorted(self.db[g])) for g in self.db.keys()})
