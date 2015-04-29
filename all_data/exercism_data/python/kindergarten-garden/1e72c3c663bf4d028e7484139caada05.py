from collections import defaultdict

class Garden(object):

    plant_letter_to_name = {
        'V': 'Violets',
        'G': 'Grass',
        'C': 'Clover',
        'R': 'Radishes'
    }

    def __init__(self, garden_definition_string, students=[
        'Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry'
    ]):
        self._students = sorted(students)
        self._definition = self.parse(garden_definition_string)

    def plants(self, student):
        return [self.plant_letter_to_name[plant] for plant in self._definition[student]]

    def parse(self, garden_definition_string):
        definition = defaultdict(str)

        for garden_row in garden_definition_string.split('\n'):
            for i in xrange(0, len(garden_row), 2):
                definition[self._students[i / 2]] += garden_row[i:i + 2]

        return definition
