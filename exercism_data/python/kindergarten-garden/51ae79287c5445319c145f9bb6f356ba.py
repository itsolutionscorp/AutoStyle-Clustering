# -*- coding: utf-8 -*-

class Garden(object):
    _children_list = [
        'Alice',  'Bob',    'Charlie', 'David',
        'Eve',    'Fred',   'Ginny',   'Harriet',
        'Ileana', 'Joseph', 'Kincaid', 'Larry'
    ]

    _plant_dict = {
        'C': 'Clover',
        'G': 'Grass',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, styrofoam_cups, students = _children_list):
        self._styrofoam_cups = styrofoam_cups.split()
        self._students = sorted(students)

    def plants(self, student):
        window_side = self._styrofoam_cups[0]
        other_side  = self._styrofoam_cups[1]

        student_index = 2 * self._students.index(student)
        initial_list  = window_side[student_index: student_index + 2] \
                        + other_side[student_index: student_index + 2]

        return [self._plant_dict[initial] for initial in initial_list]
