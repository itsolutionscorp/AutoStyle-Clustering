__author__ = 'banarasitippa'

class Garden:
    '''
    Students in alphabetical order plants their allocated plants
    from left-to-right starting with the row nearest the windows.
    '''

    def __init__(self,plants,students=None):
        '''
        constructor
        :param plants: string
        :param kids: list of strings
        :return: None
        '''
        self.window = plants.split("\n")
        if students == None:
            self.students = {"Alice": 0, "Bob": 1, "Charlie": 2, "David": 3,
                             "Eve": 4, "Fred": 5, "Ginny": 6, "Harriet": 7,
                             "Ileana": 8, "Joseph": 9, "Kincaid": 10, "Larry": 11}
        else:
            # sort them and create students dict
            self.students = {student: idx for idx, student in enumerate(sorted(students))}

    def plants(self, student):

        plant_type = {'G': "Grass", 'C': "Clover", 'R': "Radishes", 'V': "Violets"}
        # determine the position of the student
        pos = self.students[student]
        # use the position to return the plants he owns
        return [plant_type[plant] for plant in (self.window[0][pos*2:pos*2+2]+self.window[1][pos*2:pos*2+2])]
