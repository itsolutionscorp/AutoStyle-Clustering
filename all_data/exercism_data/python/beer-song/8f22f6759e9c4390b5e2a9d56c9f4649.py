class Plenty(object):

  def verse(self, num):
    return "%s of beer on the wall, %s of beer.\n" % (self.concord(num), self.concord(num)) + \
        "Take one down and pass it around, %s of beer on the wall.\n" % self.concord(num - 1)

  def concord(self, num):
    return num > 1 and "%s bottles" % num or "1 bottle"


class Last(object):

  def verse(self, num):
    return "1 bottle of beer on the wall, 1 bottle of beer.\n" + \
        "Take it down and pass it around, no more bottles of beer on the wall.\n"


class Empty(object):

  def verse(self, num):
    return "No more bottles of beer on the wall, no more bottles of beer.\n" + \
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n"


class Beer(object):

  def verse(self, num):
    return self.pick_verse(num).verse(num)

  def sing(self, fro, to=0):
    return "\n".join([self.verse(num) for num in reversed(range(to, fro + 1))]) + "\n"

  def pick_verse(self, num):
    if not num:
      return Empty()
    elif num == 1:
      return Last()
    return Plenty()
