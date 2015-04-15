PLANTS = dict(zip(['G', 'C', 'R', 'V'],
                  ['Grass', 'Clover', 'Radishes', 'Violets']))
CHILDREN = "Alice Bob Charlie David Eve Fred Ginny Harriet \
              Ileana Joseph Kincaid Larry".split()


class Garden(object):

    def __init__(self, cups, students = CHILDREN):
        self.first_row = cups[:cups.index('\n')]
        self.second_row = cups[cups.index('\n')+1:]
        self.students = sorted(students)
            
    def plants(self, child):
        child_order = self.students.index(child)
        return [PLANTS[self.first_row[2 * child_order]],
                PLANTS[self.first_row[2 * child_order + 1]],
                PLANTS[self.second_row[2 * child_order]],
                PLANTS[self.second_row[2 * child_order + 1]]]
                

if __name__ == '__main__':
    g = Garden('asdf\nkeds')
    g2 = Garden('VRD\nDER', ['Samantha', 'Alex', 'Bob'])
    
    print g.students
    print g2.students
