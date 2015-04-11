def process_rows(rows):
    """Returns processed rows (split, rearranged, & expanded)"""
    # split into tuples
    rows = [[line[i:i+2] for i in range(0, len(line), 2)] for line in rows]
    # combine like indices from first & second row
    rows = list(zip(rows[0], rows[1]))
    # return expansions
    return [''.join(row).replace('V', 'Violets ').replace('R', 'Radishes ').replace('G', 'Grass ').replace('C', 'Clover ').strip().split() for row in rows]

class Garden:
    """Models a class garden"""
    coreStudents = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

    def __init__(self, rows, students=coreStudents):
        """Creates a garden object"""
        self.rows = process_rows(rows.split('\n'))
        self.students = sorted(students)
        
    def plants(self, student):
        """Returns a student's plants"""
        index = self.students.index(student)
        # Rows index will be the same as position in sorted students list
        return self.rows[index]
