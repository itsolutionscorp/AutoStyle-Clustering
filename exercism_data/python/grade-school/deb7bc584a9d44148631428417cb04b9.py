class School:
    def __init__(self, schoolname):
        self.schoolname = schoolname
        self.tempdict = dict()
        self.leset = set()
        self.db = dict()

    def add(self, student=None, gradeN=None):
        self.student = student
        self.gradeN = gradeN

        if self.student is None and self.gradeN is None:
            return {}
        else:
            if self.gradeN in self.db:
                self.db[self.gradeN].add(self.student)
            else:
                self.leset = set()
                self.leset.add(self.student)
                self.db[self.gradeN] = set(sorted(self.leset))

    def grade(self, grade):
        try:
            return self.db[grade]
        except KeyError:
            print("Key is not in the dictionary or dictionary is empty.")
            return set()

    def sort(self):
        return {k: tuple(self.db[k]) for k in sorted(self.db)}
        # yet another way to accomplish that.
        # sortedict = dict()
        # for k in sorted(self.db):
        #     sortedict[k] = tuple(self.db[k])
        # return sortedict
        #Dict comprehension
