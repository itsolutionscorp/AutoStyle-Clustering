elements = [ ["first", "a Partridge in a Pear Tree"], ["second", "two Turtle Doves"], ["third", "three French Hens"], ["fourth", "four Calling Birds"], ["fifth", "five Gold Rings"], ["sixth", "six Geese-a-Laying"], ["seventh", "seven Swans-a-Swimming"], ["eighth", "eight Maids-a-Milking"], ["ninth", "nine Ladies Dancing"], ["tenth", "ten Lords-a-Leaping"], ["eleventh", "eleven Pipers Piping"], ["twelfth", "twelve Drummers Drumming"] ]

def verses(first, last):
  verses_list = []
  for i in range(first, last + 1):
    verses_list.append(verse(i))
    verses_list.append('\n')
  return "".join(verses_list)

def verse(num):

  nth_verse = ["On the ", elements[num-1][0], " day of Christmas my true love gave to me, "]
  for i in range(num, 1, -1):
    nth_verse.append(elements[i-1][1])
    nth_verse.append(", ")
  if num != 1:
    nth_verse.append("and ")
  nth_verse.append("a Partridge in a Pear Tree.\n")
  return "".join(nth_verse)


def sing():
  return verses(1,12)
