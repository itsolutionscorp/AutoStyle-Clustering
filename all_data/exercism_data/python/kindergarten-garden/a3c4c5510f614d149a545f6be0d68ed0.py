# Method 1: every plant in a single row
# class Garden(object):
#     def __init__(self, plant_string, students = None):
#         plant_dict = {
#             'V': "Violets",
#             'C': "Clover",
#             'R': "Radishes",
#             'G': "Grass"
#         }

#         if students:
#             students.sort()
#             self.students = students
#         else:
#             self.students = [
#                 'Alice',
#                 'Bob',
#                 'Charlie',
#                 'David',
#                 'Eve',
#                 'Fred',
#                 'Ginny',
#                 'Harriet',
#                 'Ileana',
#                 'Joseph',
#                 'Kincaid',
#                 'Larry'
#             ]

#         temp1, temp2 = plant_string.split()
#         self.plant_row = []
#         for x, y in zip(temp1, temp2):
#             self.plant_row.extend((plant_dict[x], plant_dict[y]))

#     def plants(self, plant_onwer):
#         key = self.students.index(plant_onwer) * 4
#         return self.plant_row[key:key + 4]


# Method 2: plant in two rows
class Garden(object):
    def __init__(self, plant_string, students = None):
        plant_dict = {
            'V': "Violets",
            'C': "Clover",
            'R': "Radishes",
            'G': "Grass"
        }

        if students:
            students.sort()
            self.students = students
        else:
            self.students = [
                'Alice',
                'Bob',
                'Charlie',
                'David',
                'Eve',
                'Fred',
                'Ginny',
                'Harriet',
                'Ileana',
                'Joseph',
                'Kincaid',
                'Larry'
            ]

        temp1, temp2 = plant_string.split()

        self.plant_row1 = []
        self.plant_row2 = []

        for plant in temp1:
            self.plant_row1.append(plant_dict[plant])
        for plant in temp2:
            self.plant_row2.append(plant_dict[plant])

    def plants(self, plant_onwer):
        key = self.students.index(plant_onwer) * 2
        return (self.plant_row1[key:key + 2] + self.plant_row2[key:key + 2])
