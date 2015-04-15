poem = [   ("first",    "a Partridge in a Pear Tree"),
           ("second",   "two Turtle Doves"),
           ("third",    "three French Hens"),
           ("fourth",   "four Calling Birds"),
           ("fifth",    "five Gold Rings"),
           ("sixth",    "six Geese-a-Laying"),
           ("seventh",  "seven Swans-a-Swimming"),
           ("eighth",   "eight Maids-a-Milking"),
           ("ninth",    "nine Ladies Dancing"),
           ("tenth",    "ten Lords-a-Leaping"),
           ("eleventh", "eleven Pipers Piping"),
           ("twelfth",  "twelve Drummers Drumming")]

def verse(i):
    s = "On the " + poem[i-1][0] + " day of Christmas my true love gave to me, " +\
            ", ".join(t for _,t in poem[i-1:0:-1])
    if i > 1:
        s += ", and "
    return s + poem[0][1] + ".\n"
def verses(s, e):
    return '\n'.join(verse(i) for i in xrange(s, e+1)) + '\n'
def sing():
    return verses(1, 12)
