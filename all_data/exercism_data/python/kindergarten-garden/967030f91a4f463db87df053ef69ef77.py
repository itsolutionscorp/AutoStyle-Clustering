class Garden:
    plant_dict = {'G': 'Grass',
                  'C': 'Clover',
                  'R': 'Radishes',
                  'V': 'Violets'}
    student_list = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
                    'Ginny', 'Harriet', 'Ileana', 'Joseph',
                    'Kincaid', 'Larry']
    
    def __init__(self, plant_bed, students = student_list):
        self.plant_rows = plant_bed.split()
        self.students = students
        self.students.sort()

    def plants(self, name):
        self.growing = []
        for i, student in enumerate(self.students):
            if name == student:
                for plant_row in self.plant_rows:
                    self.growing.append(plant_row[2*i])
                    self.growing.append(plant_row[2*i+1])
        return [Garden.plant_dict[x] for x in self.growing]
