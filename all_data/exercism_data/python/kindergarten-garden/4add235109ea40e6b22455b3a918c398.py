#garden.py
#There is no reason for this...really Bob is going to knock Alice's plants over while he's playing dinosaurs
#and Alice will retaliate by telling Fred to knock the rest of them down and make a new garden for her
#... don't even get me started on Larry....
#No error checking because this problem was tedious and boring and I want to move on to cool things

class Garden:

    def __init__(self, garden_string, students=None):
        self.garden_string = garden_string
        if students:
            self.students = sorted(students)
        else:
            self.students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny',
                             'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

    @staticmethod
    def slices(series, size):
        #modified from exercism: series
        if (len(series)-size) < 0 or size == 0:
            raise ValueError('Slice is too large(or zero!?), just eat the whole thing why don\'t you?')
        return [list(series[i:size+i]) for i in range(0, (len(series)-size)+1, size)]

    def plants(self, name):
        #create garden list
        plants = self.garden_string.split()
        plants[0] = self.slices(plants[0], 2)
        plants[1] = self.slices(plants[1], 2)
        plants = [plants[0][i]+plants[1][i] for i in range(0, len(plants[0]))]
        #could use translate but I already wrote it so it defeats the purpose of using something with less lines of code
        #...but then again I could have just not written this comment... oh life....
        for i in range(0, len(plants)):
            for j in range(0, len(plants[i])):
                if plants[i][j] == 'R':
                    plants[i][j] = 'Radishes'
                elif plants[i][j] == 'C':
                    plants[i][j] = 'Clover'
                elif plants[i][j] == 'G':
                    plants[i][j] = 'Grass'
                elif plants[i][j] == 'V':
                    plants[i][j] = 'Violets'

        return plants[self.students.index(name)]
