class Triangle(object):

  def __init__(self, side1, side2, side3):

    if side1 <= 0 or side2 <= 0 or side3 <= 0 or side1 >= side2 + side3 or side2 >= side1 + side3 or side3 >= side1 + side2:
      raise TriangleError
    if side1 == side2 and side1 == side3:
      self.t_kind = "equilateral"
    elif side1 == side2 or side1 == side3 or side2 == side3:
      self.t_kind = "isosceles"
    else:
      self.t_kind = "scalene"

  def kind(self):

    return self.t_kind


class TriangleError(Exception):
  pass
