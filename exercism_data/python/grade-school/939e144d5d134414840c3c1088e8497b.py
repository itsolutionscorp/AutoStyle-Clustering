class School:
    def __init__(self, school_name):
        self.name = school_name
        self.db = DB()

    def add(self, student_name, student_grade):
        self.db[student_grade] = self.db[student_grade]
        self.db[student_grade].add(student_name)

    def grade(self, student_grade):
        return self.db[student_grade]

    def sort(self):
        self.school_sorted = []
        self.grade_list = list(self.db.keys())
        self.grade_list.sort()
        for grade in self.grade_list:
            self.student_list = list(self.db[grade])
            self.student_list.sort()
            self.school_sorted.append(tuple([grade,
                                             tuple(self.student_list)]))
        return self.school_sorted

class DB(dict):
    def __missing__(self, key):
        return set()
