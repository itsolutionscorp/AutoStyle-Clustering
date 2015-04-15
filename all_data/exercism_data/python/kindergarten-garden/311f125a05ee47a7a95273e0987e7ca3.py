class Garden:
    students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny',
                'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

    plant_dict = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

    def __init__(self, formation, students=students):
        rows = formation.split()
        self.first, self.second = rows[0], rows[1]
        self.students = sorted(students)


    def plants(self, student):
        pos = self.students.index(student) * 2
        codes = self.first[pos:pos+2] + self.second[pos:pos+2]
        return [Garden.plant_dict.get(code) for code in codes]

