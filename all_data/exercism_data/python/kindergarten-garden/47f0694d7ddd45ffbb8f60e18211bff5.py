class Garden(object):
    vegetables = {'G': 'Grass', 'C': 'Clover', 'R':'Radishes', 'V':'Violets'}
    default_students = ['Alice', 'Bob', 'Charlie', 'David',
                        'Eve', 'Fred', 'Ginny', 'Harriet',
                        'Ileana', 'Joseph', 'Kincaid', 'Larry']

    def __init__(self, layout, students=None):
        self.layout = layout.splitlines()
        if not students:
            self.students = Garden.default_students
        else:
            self.students = sorted(students)
        self.dict = {name: self.layout[0][num*2:num*2+2] + self.layout[1][num*2:num*2+2]
                     for num, name in enumerate(self.students)}

    def plants(self, name):
        return [Garden.vegetables[letter] for letter in self.dict[name]]
