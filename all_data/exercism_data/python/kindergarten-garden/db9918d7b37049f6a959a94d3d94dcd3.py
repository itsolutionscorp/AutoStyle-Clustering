import string

class Garden(object):
    _plants = {
        'V': 'Violets',
        'R': 'Radishes',
        'C': 'Clover',
        'G': 'Grass',
        
    }
    def __init__(self, text, students=None):
        if students:
            self.order = ''.join(sorted(student[0].upper() for student in students))
        else:
            self.order = string.uppercase
        self.parts = text.splitlines()
    def plants(self, person):
        pos = self.order.index(person[0])
        return [self._plants.get(ch) for part in self.parts for ch in part[pos*2:pos*2+2]]
