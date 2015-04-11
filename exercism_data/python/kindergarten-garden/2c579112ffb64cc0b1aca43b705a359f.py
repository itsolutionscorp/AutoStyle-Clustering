LEGEND = {"G": "Grass", "C": "Clover", "V": "Violets", "R": "Radishes"}

class Garden:
  def __init__(self, rows, students=None):
    self._rows = rows.split()
    self._students = sorted(students or "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split())

  def plants(self, student):
    #This change alone makes it future proof. If the teacher decides that they will have 3 rows of cups instead of 2, it will still work.
    noOfCupRows = len(self._rows)
    i = self._students.index(student) * noOfCupRows
    plants = []
    for r in self._rows:
      for n in range(noOfCupRows):
        plants.append(LEGEND[r[i+n]])
    return plants
