__author__ = 'Eric'


class Garden:

    # Dict for plant types
    plant_types = {'V': 'Violets',
                   'C': 'Clover',
                   'G': 'Grass',
                   'R': 'Radishes'}

    # Default student list
    students = ['Alice', 'Bob', 'Charlie', 'David',
                'Eve', 'Fred', 'Ginny', 'Harriet',
                'Ileana', 'Joseph', 'Kincaid', 'Larry']

    def __init__(self, *args, **kwargs):
        self.layout = args[0]
        for key, value in kwargs.items():
            setattr(self, key, value)

    def plants(self, student):

        # Since every student gets two consecutive pots per row,
        # each students starting position is 2*their place in the list
        # Alice = 2*0 = 0
        # Bob =   2*1 = 2
        # etc...
        start_pos = sorted(self.students).index(student)*2

        rows = self.layout.splitlines()

        return [(Garden.plant_types[row[i]])
                for row in rows
                for i in (start_pos, start_pos+1)]
