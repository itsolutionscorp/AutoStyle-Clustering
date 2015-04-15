class School():
    """Write a small archiving program that stores students' names along with the grade that they are in."""

    def __init__(self, name):
        self.school = name
        self.db = {}
    
    def add(self, student, grade):
        "Add a student's name to the roster for a grade."
        if grade in self.db.keys():
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}
        
    def grade(self, grade):
        "Get a list of all students enrolled in a grade."
        return self.db.get(grade, set())
    
    def sort(self):
        "Get a sorted list of all students in all grades."
        sorted_grades = {}
        for grade in sorted(self.db.keys()):
            sorted_grades[grade] = tuple(sorted(self.db[grade]))
        return sorted_grades
