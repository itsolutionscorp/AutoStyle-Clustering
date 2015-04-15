class School:
    db = {}
    def __init__(self, name):
        self.school_name = name
        

    def add(self, student_name, grade_number):
        if grade_number in self.db:
            self.db[grade_number].add(student_name)
        else:
            self.db[grade_number] = set([student_name,])

    def sort(self):
        return [tuple([grade,sorted(list(self.db[grade]))]) for grade in sorted(list(self.db.keys()))]
        
    def grade(self, grade_number):
        return self.db[grade_number]
