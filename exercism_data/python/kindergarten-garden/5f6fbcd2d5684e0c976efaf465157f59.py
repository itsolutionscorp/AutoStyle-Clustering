PLANT_NAME = {'G': 'Grass',
              'C': 'Clover',
              'R': 'Radishes',
              'V': 'Violets'}

DEFAULT_STUDENTS = ('Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny',
                   'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry')


class Garden:
    """Represent a garden."""
    def __init__(self, plants, students=DEFAULT_STUDENTS):
        """Initialise a garden."""
        self._plants = plants.split()
        self._students = sorted(students)

    def _get_plant_positions(self, student):
        i = self._students.index(student)
        return  [i * 2, i * 2 + 1]

    @staticmethod
    def _convert_letter_codes_to_names(plant_codes):
        translation_table = str.maketrans(PLANT_NAME)

        plant_names = []
        for letter in plant_codes:
            plant_names.append(letter.translate(translation_table))

        return plant_names

    def plants(self, student):
        """Return which plants belongs to a specified student."""

        positions = self._get_plant_positions(student)

        plants_from_row1 = self._plants[0][positions[0]:positions[1] + 1]
        plants_from_row2 = self._plants[1][positions[0]:positions[1] + 1]

        plants_from_row1 = Garden._convert_letter_codes_to_names(plants_from_row1)
        plants_from_row2 = Garden._convert_letter_codes_to_names(plants_from_row2)

        return plants_from_row1 + plants_from_row2
