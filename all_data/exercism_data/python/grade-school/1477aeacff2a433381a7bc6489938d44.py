from collections import defaultdict, OrderedDict

class School:
  def __init__(self, schoolName):
    self.schoolName = schoolName
    self.db = defaultdict(set)

  def add(self, name, grade):
    self.db[grade].add(name)

  def grade(self, grade):
    return self.db[grade]

  def sort(self):
    db = self.db
    sortedKeys = sorted([k for k, v in db.iteritems()])
    return OrderedDict((k, tuple(db[k])) for k in sortedKeys)
