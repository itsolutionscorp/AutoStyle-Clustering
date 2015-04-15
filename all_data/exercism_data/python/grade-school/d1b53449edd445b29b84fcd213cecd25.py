class School:
    """ School stores students' names along with the grade that they are in. """

    def __init__(self, schoolname):
        self.schoolname = schoolname
        self.db = {}

    def add(self, name, grade):
        if grade in self.db:
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        else:
            return set()

    def sort(self):
        sorted_db = {}
        for grade in self.db:
            sorted_db[grade] = tuple(sorted(self.db[grade]))
        return sorted_db
