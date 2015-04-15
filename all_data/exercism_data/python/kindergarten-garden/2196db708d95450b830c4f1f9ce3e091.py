class Garden:

    # Default students
    _students = ['Alice', 'Bob', 'Charlie', 'David',
                 'Eve', 'Fred', 'Ginny', 'Harriet',
                 'Ileana', 'Joseph', 'Kincaid', 'Larry']
    _row1 = None
    _row2 = None

    _plantsNames = {
        'V': 'Violets',
        'R': 'Radishes',
        'C': 'Clover',
        'G': 'Grass'
        }
    
    def __init__(self, plants, students = None):
        if students is not None:
            self._students = sorted(students)
        (self._row1, self._row2) = plants.split()
    
    def plants(self, student):
        if student in self._students:
            i = self._students.index(student)
            plants = self._row1[i * 2: i * 2 + 2] + self._row2[i * 2: i * 2 + 2]
            return list(self._plantsNames[p] for p in plants)
        else:
            raise ValueError("Student not found")
