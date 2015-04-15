from operator import concat

plant_lookup = {
    'C': 'Clover',
    'G': 'Grass',
    'R': 'Radishes',
    'V': 'Violets'
}

class Garden(object):
    def __init__(self, garden, students=None):
        self.students = sorted(students or [])
        self.rows = garden.split()

    def plants(self, student):
        offset = self._index(student) * 2
        return [
            plant_lookup[row[offset + i]]
            for row in self.rows
            for i in range(2)
        ]

    def _index(self, student):
        if self.students:
            return self.students.index(student)

        return ord(student[0]) - ord('A')
