from string import ascii_uppercase

FLOWER_NAMES = {f[0]: f for f in ['Clover','Grass','Radishes','Violets']}

class Garden(object):
  
  def __init__(self, garden, students=None):
    row_1, row_2 = garden.split('\n')
    it_1, it_2 = iter(row_1), iter(row_2)
    # top row x2, bottom row x2
    self.blocks = [''.join(o) for o in zip(it_1, it_1, it_2, it_2)]
    self.students = sorted([student[0] for student in students]) if students else ascii_uppercase[:12]

  def plants(self, name):
    p = self.blocks[self.students.index(name[0])] # eg. 'VRGG'
    return [FLOWER_NAMES[f] for f in p]
