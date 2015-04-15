import string
from collections import OrderedDict
class Garden:
    flowers = OrderedDict([("V", "Violets"),
                    ("C", "Clover"),
                    ("G", "Grass"),
                    ("R", "Radishes")])

    student_list = OrderedDict([])
    
    def __init__(self, garden, students=None):
        self.students = students
        self.garden = garden.splitlines()
        self.gardens = []
        self.list1 = list(self.garden[0])
        self.list2 = list(self.garden[1])
        while len(self.list1) >= 2:
            self.gardens.append(str(self.list1[0] + self.list1[1] + self.list2[0] + self.list2[1]))
            del self.list1[0:2:1]
            del self.list2[0:2:1]
        self.alpha2num = dict(zip(string.ascii_lowercase, range(1, 27)))
        
    def plants(self, name):
        if self.students == None:
            value = self.alpha2num[name[:1].lower()] - 1
        else:
            self.students = sorted(self.students)
            for x, y in enumerate(self.students):
                self.student_list.update({y:x})
            value = self.student_list[name]
        result = self.replace_all(" ".join(self.gardens[value]), self.flowers)
        return result.split()
    
    def replace_all(self, text, dic):
        for i, j in dic.items():
            text = text.replace(i, j)
        return text
