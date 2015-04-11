class Garden(object):

  def __init__(self, diagram, students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]):
    self.diagram = diagram.split()
    self.students = sorted(students)
    self.all_plants = {'G': "Grass", 'C': "Clover", 'R': "Radishes", 'V': "Violets"}
    self.stud_plants = {}
    i = 0
    j = 0
    while (i < len(self.diagram[0])):
      student = self.students[j]
      self.stud_plants[student] = []
      self.stud_plants[student].append(self.all_plants[self.diagram[0][i]])
      self.stud_plants[student].append(self.all_plants[self.diagram[0][i+1]])
      self.stud_plants[student].append(self.all_plants[self.diagram[1][i]])
      self.stud_plants[student].append(self.all_plants[self.diagram[1][i+1]])
      i += 2
      j += 1
    

  def plants(self, student):
 
    return self.stud_plants[student]    
