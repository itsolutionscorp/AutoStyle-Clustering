class School:
    database = {}
    
    def __init__(self,school_name):
        self.school_name = school_name
        self.database = {}
        
    @property
    def db(self):
        return self.database
        
    def add(self,student_name,student_grade):
        if student_grade in self.database:
            self.database[student_grade].add(student_name)
        else:
            self.database[student_grade] = {student_name}
    
    def grade(self,grade_number):
        if grade_number not in self.database:
            return set()
        return self.database[grade_number]
        
    def sort(self):
        return {
            grade: tuple(students)
            for grade,students in list(sorted(self.database.items()))
            }
