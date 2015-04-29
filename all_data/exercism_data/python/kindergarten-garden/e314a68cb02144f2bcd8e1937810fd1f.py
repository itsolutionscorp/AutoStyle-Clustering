"""Kindergarten Garden"""


STUDENTS = tuple(sorted([
    "Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet",
    "Ileana", "Joseph", "Kincaid", "Larry"
]))


class Garden(object):
    """Kindergarten Garden"""

    PLANTS = {
        plant[0]: plant
        for plant in ["Grass", "Clover", "Radishes", "Violets"]
    }

    PLANTS_PER_ROW = 2

    def __init__(self, rows, students=None):
        self.rows = rows.split()
        assert all(len(row) == len(self.rows[0]) for row in self.rows)
        assert all(
            plant in Garden.PLANTS for row in self.rows for plant in row
        )
        self.students = sorted(students) if students is not None else STUDENTS

    def __getitem__(self, student):
        col = self.students.index(student) * Garden.PLANTS_PER_ROW
        return [
            Garden.PLANTS[row[c]]
            for row in self.rows
            for c in xrange(col, col + Garden.PLANTS_PER_ROW)
        ]

    def plants(self, student):
        """Return plants which the student is responsible for."""
        return self[student]
