class School:
    def __init__(self, school_name):
        self.name = school_name
        self.db = {}

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade] = self.db[grade] | {student}
        else:
            self.db[grade] = {student}

    def grade(self, year):
        return self.db.get(year, set())

    def sort(self):
        sort_dict = {}
        for grade in self.db:
            sort_dict[grade] = tuple(sorted(self.db[grade]))
        return sort_dict
