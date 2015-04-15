"""
garden - a module for kindergarten gardening.
"""

class Garden(object):
    """
    A basic garden object for our specific garden type.
    """

    # Covert the plant alphabetic code to a plant's full name.
    plant_lut = {'C': "Clover",
                 'G': "Grass",
                 'R': "Radishes",
                 'V': "Violets"}
    
    def __init__(self, plants_string, students=None):
        if students:
            self.students = sorted(students)
        else:
            self.students = ["Alice",
                             "Bob",
                             "Charlie",
                             "David",
                             "Eve",
                             "Fred",
                             "Ginny",
                             "Harriet",
                             "Ileana",
                             "Joseph",
                             "Kincaid",
                             "Larry"]
        self.student_plants = {student: [] for student in self.students}
        rows = plants_string.split()
        for row in rows:
            length = len(row)
            for i in xrange(length / 2):
                a = row[i * 2]
                b = row[i * 2 + 1]
                self.student_plants[self.students[i]].append(a)
                self.student_plants[self.students[i]].append(b)

    def plants(self, student):
        """
        Return a list of plants cared for by a given student.
        """

        return [Garden.plant_lut[c] for c in self.student_plants[student]]
