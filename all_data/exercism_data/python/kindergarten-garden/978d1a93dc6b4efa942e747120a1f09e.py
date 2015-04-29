PLANTS = {
    'R': 'Radishes',
    'C': 'Clover',
    'G': 'Grass',
    'V': 'Violets'
}

class Garden(object):
    def __init__(self, plants, students=None):
        self.assignments = self.__assign(students) if students else self.__default_assignments()
        self.window_row = list(plants.split("\n")[0])
        self.back_row = list(plants.split("\n")[1])

    def plants(self, name):
        return [PLANTS[self.window_row[place]] for place in self.assignments[name]] + [PLANTS[self.back_row[place]] for place in self.assignments[name]]

    def __default_assignments(self):
        return {
        'Alice': [0, 1],
        'Bob': [2, 3],
        'Charlie': [4, 5],
        'David': [6, 7],
        'Eve': [8, 9],
        'Fred': [10, 11],
        'Ginny': [12, 13],
        'Harriott': [14, 15],
        'Ileana': [16, 17],
        'Joseph': [18, 19],
        'Kincaid': [20, 21],
        'Larry': [22, 23]
        }

    def __assign(self, students):
        assignments = {}
        place = 0

        for student in sorted(students):
            assignments[student] = [place, place+1]
            place += 2

        return assignments
