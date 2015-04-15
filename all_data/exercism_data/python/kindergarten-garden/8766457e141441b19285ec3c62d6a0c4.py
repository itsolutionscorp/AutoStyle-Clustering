class Garden:
    def __init__(self, garden_string, students=None):
        self.garden_string = garden_string
        self.students = students
        self.garden_display = []
        self.plant_list = []
        self.plant_names = {"G": "Grass", "C": "Clover", "R": "Radishes", "V": "Violets"}
        self.plant_names_list = []

    def student_list(self, students):
        n = 0
        class_list = {}
        self.students.sort()
        for person in students:
            class_list[person] = n
            n += 2
        return class_list

    def plants(self, name):

        if not self.students:
            self.class_list = {"Alice": 0, "Bob": 2, "Charlie": 4, "David": 6,
                            "Eve": 8, "Fred": 10, "Ginny": 12, "Harriet": 14,
                            "Ileana": 16, "Joseph": 18, "Kincaid": 20, "Larry": 22}
        else:
            self.class_list = self.student_list(self.students)

        if name in self.class_list:
            plant_sub_list_1 = []
            plant_sub_list_2 = []
            print name
            n = self.class_list[name]
            for chunk in self.garden_string.split("\n"):
                    plant_sub_list_1.append(chunk[n])
                    plant_sub_list_1.append(chunk[n+1])

            for plant in plant_sub_list_1:
                if plant in self.plant_names:
                    plant_sub_list_2.append((self.plant_names[plant]))

            return plant_sub_list_2

__author__ = 'tracyrohlin'
