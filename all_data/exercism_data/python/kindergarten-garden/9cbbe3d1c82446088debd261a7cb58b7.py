class Garden:

    _plant_codes = {'C': "Clover",
                    'G': "Grass",
                    'R': "Radishes",
                    'V': "Violets"}

    def __init__(self, spec, students=['Alice',
                                       'Bob',
                                       'Charlie',
                                       'David',
                                       'Eve',
                                       'Fred',
                                       'Ginny',
                                       'Harriet',
                                       'Ileana',
                                       'Joseph',
                                       'Kincaid',
                                       'Larry']):
        self.spec = spec
        self.students = sorted(students)
        self._student_ids = None
        self._rows = None

    @property
    def student_ids(self):
        if self._student_ids is None:
            self._student_ids = dict(zip(self.students,
                                         range(len(self.students))))
        return self._student_ids

    @property
    def rows(self):
        if self._rows is None:
            self._rows = [list(row) for row in self.spec.split('\n')]
        return self._rows

    def plants(self, student):
        student_id = self.student_ids.get(student)
        row_index = student_id * 2
        codes = (self.rows[0][row_index:row_index+2]
                 + self.rows[1][row_index:row_index+2])
        return list(map(self.plant_name, codes))

    def plant_name(self, code):
        return self._plant_codes.get(code)
