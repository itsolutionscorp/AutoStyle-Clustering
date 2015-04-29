MAPPING = {"G": "Grass", "C": "Clover", "V": "Violets", "R": "Radishes"}

class Garden:
  def __init__(self, rows, *args, **kwargs):
    self._rows = rows.split()
    if kwargs:
      self._students = sorted(kwargs['students'])
    else:
      self._students = sorted("Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split())

  def plants(self, student):
    i = self._students.index(student) * 2
    cups = ""
    for r in self._rows:
      cups += r[i: i + 2]

    return [MAPPING[c] for c in cups.upper()]
