from collections import OrderedDict

class Garden:
    """Write a program that, given a diagram, can tell you which plants each child in the kindergarten class is responsible for."""
    std_class = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
    plant_list = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}
    
    def __init__(self, diagram, students = None):
        self.diagram = list(diagram)
        if students != None:
            self.order = OrderedDict(zip(sorted(students), range(0, len(students)*2+1, 2)))
        else:
            self.order = OrderedDict(zip(self.std_class, range(0, len(self.std_class)*2+1, 2)))
                
    def plants(self, child):
        plant_idx = [self.order[child], self.order[child] + 1, self.order[child] + self.diagram.index('\n') + 1, self.order[child] + self.diagram.index('\n') + 2]
        return [self.plant_list[self.diagram[idx]] for idx in plant_idx]
        
