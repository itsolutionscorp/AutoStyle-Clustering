_DEFAULT_STUDENTS = [
        'Alice', 'Bob', 'Charlie', 'David',
        'Eve', 'Fred', 'Ginny', 'Harriet',
        'Ileana', 'Joseph', 'Kincaid', 'Larry'
]

_PLANT_MAP = {
        'C': 'Clover', 
        'G': 'Grass', 
        'R': 'Radishes', 
        'V': 'Violets'
}


def _get_plant(letter):
    return _PLANT_MAP[letter]


class Garden:
    def __init__(self, layout, students=None):
        self.layout = layout
        if students:
            self.students = sorted(students)
        else:
            self.students = _DEFAULT_STUDENTS
        self.create_garden()

    def create_garden(self):
        """Build a map of student to plant list based on layout and student list.
        This is incredibly fragile in that edge cases list uneven rows or 
        insufficiently large student list are not handled.
        """
        self.garden = {}
        for r in self.layout.split("\n"):
            for i, p in enumerate(zip(r[::2], r[1::2])):
                index = self.students[i]
                if index not in self.garden:
                    self.garden[index] = []
                self.garden[index].extend(map(_get_plant, p))

    def plants(self, name):
        return self.garden[name]


    def plants2(self, name):
        """Secondary solution that searches layout every time.
        This one gets the student's index and grabs the right plants directly 
        from the layout.
        """
        start_index = self.students.index(name) * 2
        g = []
        for r in self.layout.split("\n"):
            g.extend(map(_get_plant, r[start_index:start_index+2]))
        return g
