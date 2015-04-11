class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, kid, grade):
        if grade in self.db:
            self.db[grade].add(kid)
        else:
            self.db[grade] = set([kid])

    def sort(self):
        grades = []
        for key in sorted(self.db.keys()):
            students = []
            for student in sorted(self.db[key]):
                students.append(student)
            grades.append((key, tuple(students)))
        return grades

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        else:
            return set()
