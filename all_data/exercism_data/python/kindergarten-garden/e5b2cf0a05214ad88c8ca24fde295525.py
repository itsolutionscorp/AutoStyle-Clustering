DEFAULT_STUDENTS = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph",
                    "Kincaid", "Larry"]
SEEDS = {'C': 'Clover', 'G': 'Grass', 'R': 'Radishes', 'V': 'Violets'}


class Garden:
    def __init__(self, cups, students=DEFAULT_STUDENTS):
        self.cups = cups
        self.students = sorted(students)

    def plants(self, student):
        index_in_row = self.students.index(student) * 2
        return [SEEDS[cup] for row in self.cups.splitlines() for cup in row[index_in_row:index_in_row + 2]]
