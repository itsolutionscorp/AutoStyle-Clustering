carddict = {1: "first", 2: "second", 3: "third", 4: "fourth", 5: "fifth", 6: "sixth", 7: "seventh", 8: "eighth", 9: "ninth", 10: "tenth", 11: "eleventh", 12: "twelfth"}
songdict = {1: "and a Partridge in a Pear Tree", 2: "two Turtle Doves", 3: "three French Hens", 4: "four Calling Birds", 5: "five Gold Rings", 6: "six Geese-a-Laying", 7: "seven Swans-a-Swimming", 8: "eight Maids-a-Milking", 9: "nine Ladies Dancing", 10: "ten Lords-a-Leaping", 11: "eleven Pipers Piping", 12: "twelve Drummers Drumming"}

lone1 = 'a Partridge in a Pear Tree'

def verse(n):
    start = 'On the {0} day of Christmas my true love gave to me'.format(carddict[n])
    things = [songdict[i] for i in xrange(n,0,-1)]
    verse = ", ".join([start]+things) + '.\n' if n > 1 else ", ".join([start,lone1]) + '.\n'
    return verse

def verses(n,m):
    return '\n'.join([verse(i) for i in xrange(n,m+1)]) + '\n'

def sing():
    return verses(1,12)
