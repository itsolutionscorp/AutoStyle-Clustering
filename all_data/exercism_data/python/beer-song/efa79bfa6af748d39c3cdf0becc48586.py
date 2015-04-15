class Beer(object):
  def __init__(self):
    self._on_the_wall = " on the wall"
    self._comma = ", "
    self._fullstop = ".\n"
  
  def verse(self, amount):
    self._amount = amount
    return ( self._n_bottles_of_beer().capitalize()
           + self._on_the_wall
           + self._comma
           + self._n_bottles_of_beer()
           + self._fullstop
           + self._action()
           + self._comma
           + self._n_bottles_of_beer()
           + self._on_the_wall
           + self._fullstop
           ) ;
  
  def _n_bottles_of_beer(self):
    return self._number() + " " + self._bottle() + " of beer"
  
  def _number(self):
    if self._amount == 0:
      return "no more"
    else:
      return str(self._amount)
  
  def _action(self):
    if self._amount == 0:
      desciption = "Go to the store and buy some more"
      self._amount = 99
    else:
      desciption = "Take " + self._pronomial() + " down and pass it around"
      self._amount = self._amount - 1
    return desciption
  
  def _pronomial(self):
    if self._amount == 1:
      return "it"
    else:
      return "one"
  
  def _bottle(self):
    return "bottle" + ("s" if self._amount != 1 else "")
  
  def sing(self, start, end = 0):
    result = ""
    for i in range(start,end-1,-1):
      result = result + self.verse(i) + "\n"
    return result
  
