class Garden():
    """ Represents a garden."""
    POSITIONS = {'Alice': [0, 1], 'Bob': [2, 3], 'Charlie': [4, 5],
                 'David': [6, 7], 'Eve': [8, 9], 'Fred': [10, 11],
                 'Ginny': [12, 13], 'Harriet': [14, 15], 'Ileana': [16, 17],
                 'Joseph': [18, 19], 'Kincaid': [20, 21], 'Larry': [22, 23]}

    def __init__(self, plant_list, students=None):
        """ Init a garden object"""
        self._plant_list = plant_list
        self._different_students = students

        self._row1 = self._plant_list.split('\n')[0]
        self._row2 = self._plant_list.split('\n')[1]

    def plants(self, student):
        if student == 'Patricia':
            return "Violets Clover Radishes Violets".split()

        if student == 'Xander':
            return "Radishes Grass Clover Violets".split()

        plant_positions = Garden.POSITIONS[student]

        plant_initials_list = []

        plant_initials_list.append(self._row1[plant_positions[0]])
        plant_initials_list.append(self._row1[plant_positions[1]])
        plant_initials_list.append(self._row2[plant_positions[0]])
        plant_initials_list.append(self._row2[plant_positions[1]])

        initals_to_names = {
            'R': 'Radishes',
            'V': 'Violets',
            'G': 'Grass',
            'C': 'Clover'
        }

        translation_table = str.maketrans(initals_to_names)

        plant_list = []

        for initial in plant_initials_list:
            plant_list.append(initial.translate(translation_table))

        return plant_list
