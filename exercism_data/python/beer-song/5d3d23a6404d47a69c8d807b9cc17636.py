class Beer(object):

  def verse(self, num):
    return self.first(num) + self.second(num)

  def sing(self, fro, to=0):
    return "\n".join([self.verse(num) for num in reversed(range(to, fro + 1))]) + "\n"

  def first(self, num):
    return "%s of beer on the wall, %s of beer.\n" \
        % (self.bottles(num).capitalize(), self.bottles(num))

  def second(self, num):
    if num:
      return "Take %s down and pass it around, %s of beer on the wall.\n" \
          % (self.it(num - 1), self.bottles(num - 1))
    return "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

  def it(self, num):
    return num == 0 and "it" or "one"

  def bottles(self, num):
    if num == 0:
      return "no more bottles"
    return num > 1 and "%s bottles" % num or "1 bottle"
