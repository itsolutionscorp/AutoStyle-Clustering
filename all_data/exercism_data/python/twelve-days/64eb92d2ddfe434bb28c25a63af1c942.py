from string import Template

def verse(num):

    s = Template('On the $when day of Christmas my true love gave to me, ${what}.\n')

    day = {
      1: "first",
      2: "second",
      3: "third",
      4: "fourth",
      5: "fifth",
      6: "sixth",
      7: "seventh",
      8: "eighth",
      9: "ninth",
      10: "tenth",
      11: "eleventh",
      12: "twelfth"}

    thing = {
      1: "a Partridge in a Pear Tree",
      2: "two Turtle Doves",
      3: "three French Hens",
      4: "four Calling Birds",
      5: "five Gold Rings",
      6: "six Geese-a-Laying",
      7: "seven Swans-a-Swimming",
      8: "eight Maids-a-Milking",
      9: "nine Ladies Dancing",
      10: "ten Lords-a-Leaping",
      11: "eleven Pipers Piping",
      12: "twelve Drummers Drumming"}

    if num == 1:
        return s.substitute(when=day[num], what=thing[num])
    else:
        list = [thing[x] for x in range(num,1,-1)]
        list.append('and a Partridge in a Pear Tree')
        return s.substitute(when=day[num], what=', '.join(list))

def verses(start, end):
    s = '\n'.join([verse(num) for num in range(start,end+1)])
    return s+'\n'

def sing():
    return verses(1,12)
    
