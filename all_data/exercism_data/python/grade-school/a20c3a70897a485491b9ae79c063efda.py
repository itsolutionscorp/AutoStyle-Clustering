class School(object):


  def __init__(self, name):
    self.name = name
    self.db = {}


  def add(self, student, num_grade):
    if num_grade not in self.db:
      self.db[num_grade] = set()
    self.db[num_grade].add(student)


  def grade(self, num_grade):
    if num_grade in self.db: 
      return self.db[num_grade]
    else:
      return set()


  def sort(self):
    sorted_students = {}
    for grade in sorted(self.db.keys()):
      sorted_students[grade] = tuple(sorted(self.db[grade]))
    return sorted_students
