class Beer(object):
  REFILL = 99
  TEMPLATE = ("{current} of beer on the wall, {current} of beer.\n" +
              "{action}, {remaining} of beer on the wall.\n")

  def verse(self, n):
    return self._ucfirst(self.TEMPLATE.format(
      current=self._format_quantity(n),
      action=self._format_action(n),
      remaining=self._format_quantity(self.REFILL if n == 0 else n - 1)))

  def sing(self, start, to=0):
    return "\n".join(self.verse(n) for n in range(start, to - 1, -1)) + "\n"

  def _format_action(self, n):
    if n == 0:
      return "Go to the store and buy some more";
    return "Take {number} down and pass it around".format(
      number="it" if n == 1 else "one")

  def _format_quantity(self, n):
    if n == 0:
      return "no more bottles";
    return "{number} bottle{plural}".format(number=n,
                                            plural="s" if n > 1 else "");

  def _ucfirst(self, string):
    return string[0].upper() + string[1:]
