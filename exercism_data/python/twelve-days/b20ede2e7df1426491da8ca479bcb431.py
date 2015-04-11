# returns something along the
# lines of first, tenth, etc
def dayth(n):
  return [None, "first", "second", "third",
          "fourth", "fifth", "sixth",
          "seventh", "eighth", "ninth",
          "tenth", "eleventh", "twelfth"][n]

GIFTS =  ["a Partridge in a Pear Tree",
          "two Turtle Doves",
          "three French Hens",
          "four Calling Birds",
          "five Gold Rings",
          "six Geese-a-Laying",
          "seven Swans-a-Swimming",
          "eight Maids-a-Milking",
          "nine Ladies Dancing",
          "ten Lords-a-Leaping", 
          "eleven Pipers Piping",
          "twelve Drummers Drumming"]

def verse(n):
  gifts = GIFTS[0:n]
  if n > 1:
    gifts[0] = "and " + gifts[0]
  gifts = ', '.join(reversed(gifts)) 
  return "On the {} day of Christmas my true love gave to me, {}.\n".format(
      dayth(n), gifts
      )
def verses(start, end):
  return '\n'.join([verse(i) for i in xrange(start, end+1)]) + '\n'

def sing():
  return verses(1, 12)
