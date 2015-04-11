class School:
    def __init__(self,name):
        self.name = name
        self.db = {}

    def grade(self,grd):
        if grd not in self.db:
            return set()

        for grade in self.db:
            if grd == grade:
                return self.db[grd]

    def add(self,student, grd):
        if grd in self.db:
            self.db[grd].add(student)
        else:
            self.db[grd] = {student}
    def sort(self):
        for grade in self.db:
            self.db[grade] = tuple(sorted(self.db[grade]))
        return self.db
