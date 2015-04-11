class Garden:
    plant_names = dict(
        [('C', "Clover"), ('G', "Grass"), ('R', "Radishes"), ('V', "Violets")])

    def __init__(self, rows_in, students=None):
        self.rows = rows_in.splitlines()
        self.students = (sorted(students) if students else
                         ["Alice", "Bob", "Charlie", "David", "Eve", "Fred",
                          "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"])

    def plants(self, student):
        row_index = self.students.index(student) * 2
        plants = ''.join([row[row_index: row_index + 2]
                          for row in self.rows])
        return [self.plant_names[plant]
                for plant in plants]

'''
print(Garden("RC\nGG").plants("Alice"))
print(Garden("RC\nGG").plants("Bob"))
'''
