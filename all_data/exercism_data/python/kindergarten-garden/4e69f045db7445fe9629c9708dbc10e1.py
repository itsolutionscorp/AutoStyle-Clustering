_DEFAULT_STUDENTS = ("Alice Bob Charlie David Eve Fred Ginny Harriet Ileana " +
                     "Joseph Kincaid Larry").split()


_MAP_LETTER_TO_PLANT_NAME = {
        "R": "Radishes",
        "C": "Clover",
        "G": "Grass",
        "V": "Violets"
}


class Garden(object):
    def __init__(self, plant_list, students=_DEFAULT_STUDENTS):
        self._students = sorted(students)
        _plant_letters_by_level = plant_list.split("\n")
        self._plant_letters_by_level = []
        for level in _plant_letters_by_level:
            level = [letter for letter in level]
            self._plant_letters_by_level.append(level)

    def plants(self, student_name):
        student_index = next(
                index for index, student in enumerate(
                    self._students) if student == student_name)
        letters = [
                self._plant_letters_by_level[0][student_index * 2],
                self._plant_letters_by_level[0][student_index * 2 + 1],
                self._plant_letters_by_level[1][student_index * 2],
                self._plant_letters_by_level[1][student_index * 2 + 1]]
        return [_MAP_LETTER_TO_PLANT_NAME[letter] for letter in letters]
