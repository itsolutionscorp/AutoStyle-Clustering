class Garden(object):
    """ Represents a kindergarten class garden."""
    default_students = ["Alice", "Bob", "Charlie", "David",
                        "Eve", "Fred", "Ginny", "Harriet",
                        "Ileana", "Joseph", "Kincaid", "Larry"]

    def __init__(self, garden, students=default_students):
        self.students = sorted(students)
        [self.row1, self.row2] = garden.split("\n")
        self.ownership = {}
        for i, s in enumerate(self.students):
            if (2*i < len(self.row1)):
                self.ownership[s] = [self.__letter_to_plant(self.row1[2*i]),
                                     self.__letter_to_plant(self.row1[2*i+1]),
                                     self.__letter_to_plant(self.row2[2*i]),
                                     self.__letter_to_plant(self.row2[2*i+1])]

    def plants(self, student):
        return self.ownership[student]

    def __letter_to_plant(self, letter):
        if (letter == 'G'):
            return "Grass"
        elif (letter == 'R'):
            return "Radishes"
        elif (letter == 'C'):
            return "Clover"
        elif (letter == 'V'):
            return "Violets"
