class School:
    def __init__(self, name):
        self.school_name = name
        self.db = {}
        

    def add(self, student_name, grade_number):
        if grade_number in self.db:
            self.db[grade_number].add(student_name)
        else:
            self.db[grade_number] = set([student_name,])

    def sort(self):
        return [tuple([grade,tuple(sorted(list(self.db[grade])))]) for grade in sorted(list(self.db.keys()))]
        
    def grade(self, grade_number):
        if grade_number in self.db:
            return self.db[grade_number]
        else:
            return set()
