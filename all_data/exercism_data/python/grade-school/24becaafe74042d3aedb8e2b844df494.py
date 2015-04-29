class School:

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade] = set(sorted(list(self.db[grade]) + [student]))
        else:
            self.db[grade] = {student}

    def grade(self, req_grade):
        if req_grade in self.db:
            return set(self.db[req_grade])
        else:
            return set()

    def sort(self):
        result = []
        for grade in self.db:
            result.append((grade, tuple(sorted(self.db[grade]))))
        return result
