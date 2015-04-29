from collections import OrderedDict

# Representation of a school, which stores its name and the list of students per grade
class School:


    # Initialize the object, given its name
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = dict()

    # Add a new student to a particular grade
    def add(self, student, grade_number):

        # Check if the grade is already present. Create it otherwise.
        if grade_number not in self.db:
            self.db[grade_number] = set()

        # Add the student to that grade
        self.db[grade_number].add(student)

    # Return the list of students for a particular grade
    def grade(self,grade_number):
        return set() if grade_number not in self.db else self.db[grade_number]

    # Return a list of all the students, sorted by grade (and then by alphabetical order)
    def sort(self):
        sorted_grades = OrderedDict()

        for grade in self.db:
            sorted_grades[grade] = tuple([student for student in self.db[grade]])
        return sorted_grades
