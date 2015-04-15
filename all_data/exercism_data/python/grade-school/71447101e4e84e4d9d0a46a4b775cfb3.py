class School:
    
    def __init__(self,name):
        self.name = name
        self.db = {}

    def add(self,student,grade_n):
        all_in_grade = self.grade(grade_n)
        all_in_grade.add(student)
        self.db[grade_n] = all_in_grade
        
    def grade(self,grade_n):
        return self.db.get(grade_n, set())
    
    def sort(self):
        sorted_db = {}
        for grade_n in sorted(self.db.keys()):
            sorted_db[grade_n] = tuple(sorted(self.db[grade_n]))
        return sorted_db
