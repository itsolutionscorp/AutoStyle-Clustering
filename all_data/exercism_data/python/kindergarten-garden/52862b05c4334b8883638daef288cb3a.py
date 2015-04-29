from string import maketrans,translate

class Garden:
  children = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

  def __init__(self,garden,**args):
    students = self.children
    for key,value in args.iteritems():
      if 'students' is str(key):
        value.sort()
        students = value
    
    self.rows = garden.split("\n")
  
    self.child_to_plant_map = dict((child,"") for child in students)
    
    for row in self.rows:
      i = 0
      j = 2
      for child in students:
        self.child_to_plant_map[child] += row[i:j]
        i += 2
        j += 2

  def plants(self, child):
    return self.get_full_veg_names(self.child_to_plant_map.get(child))
  
  def get_full_veg_names(self,short_names):
    short_to_long_name_map = {'R': 'Radishes','V': 'Violets','G': 'Grass','C': 'Clover'}
    output = []
    for letter in short_names:
      output.append(short_to_long_name_map[letter])
    return output
    
  def get_map(self):
    return self.child_to_plant_map
