class Beer:
  def __init__(self):
    self.templates = [
      [
        "No more bottles of beer on the wall, no more bottles of beer.\n",
        "1 bottle of beer on the wall, 1 bottle of beer.\n",
        "{0} bottles of beer on the wall, {0} bottles of beer.\n",
      ],
      [
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n",
        "Take it down and pass it around, no more bottles of beer on the wall.\n",
        "Take one down and pass it around, {0} bottle{1} of beer on the wall.\n",
      ]
    ]

  def render(self, template, count):
    return template.format(count, "" if count == 1 else "s")

  def sing(self, start, end=0):
    song = []
    for i in range(start, end-1, -1):
      song.append(self.verse(i))
    return "\n".join(song) + "\n"

  def verse(self, count):
    index = 2
    if (count == 0):
      index = 0
    elif (count == 1):
      index = 1
    return self.render(self.templates[0][index], count) + self.render(self.templates[1][index], count - 1)
