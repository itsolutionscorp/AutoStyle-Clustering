class Garden:

    def __init__(self, plant_rows, students=None):
        self.plant_rows = plant_rows.split('\n')
        if not students:
            self.children = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph',
                'Kincaid', 'Larry']
        else:
            self.children = sorted(students)

    def plants(self, student):
        index = self.children.index(student)
        firstLetters = self.plant_rows[0][2*index:2*index+2] + self.plant_rows[1][2*index:2*index+2]
        return [{'V':'Violets','R':'Radishes','C':'Clover','G':'Grass'}[firstLetter] for firstLetter in firstLetters]
