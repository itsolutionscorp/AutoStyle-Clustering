'''
garden.py

kindergarten garden
'''
from itertools import izip

DEFAULT_STUDENTS=[
    "Alice",
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
    "Larry"
]

PLANT_DICT = {
    'G': "Grass",
    'C': "Clover",
    'R': "Radishes",
    'V': "Violets"
}

class Garden():

    def __init__(self, garden, students=DEFAULT_STUDENTS):
        self._students = {}
        sorted_students = sorted(students)
        for row in garden.splitlines():
            for student, p in izip(sorted_students, self._group(row, 2)):
                plants = [PLANT_DICT[c] for c in p]
                self._students.setdefault(student, []).extend(plants)

    def _group(self, s, n):
        # grouper from itertools recipes
        return zip(*([iter(s)] * n))

    def plants(self, child):
        return self._students.get(child, [])
