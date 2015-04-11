class Garden:

    def __init__(self, window_layout, **kwargs):

        self.window_layout = window_layout.replace('\n', '')
        self.plant_mapping = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

        if kwargs:
            for index, value in kwargs.items():
                if index == 'students':
                    self.students = value
        else:
            self.students = "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()

    def plants(self, student):

        self.students.sort()
        student_val = self.students.index(student)

        row1 = int(student_val * 2)
        row2 = int((student_val * 2) + ((len(self.window_layout)) / 2))

        return [self.plant_mapping[x]
                for x in [self.window_layout[x]
                          for x in (row1, row1+1, row2, row2+1)]]
