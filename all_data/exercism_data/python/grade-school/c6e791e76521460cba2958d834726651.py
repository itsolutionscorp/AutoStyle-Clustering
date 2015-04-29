class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student_name, grade_num):
        if not grade_num in self.db:
            self.db[grade_num] = {student_name}
        else:
            self.db[grade_num].add(student_name)

    def grade(self, gradeNum):
        if gradeNum in self.db:
            return self.db[gradeNum]
        else:
            return set()

    def sort(self):
        return {gradeNum: tuple(sorted(self.db[gradeNum]))
                for gradeNum in self.db.keys()}
