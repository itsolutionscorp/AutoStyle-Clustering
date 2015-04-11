gifts = [
    "a Partridge in a Pear Tree.\n",
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
    "twelve Drummers Drumming"
]

days = [
    "first",
    "second",
    "third",
    "fourth",
    "fifth",
    "sixth",
    "seventh",
    "eighth",
    "ninth",
    "tenth",
    "eleventh",
    "twelfth"
]

def verse(day):
    d = day-1

    res = ["On the %s day of Christmas my true love gave to me" % (days[d],)]
    if d == 0:
        res.append(gifts[0])
    else:
        for g in xrange(d,0,-1):
            res.append(gifts[g])
        res.append('and %s' % (gifts[0],))
            
    return ', '.join(res)
    
def verses(day_start, day_end):
    if not day_end:
        day_end=day_start

    res = []
    for d in xrange(day_start, day_end+1):
        res.append(verse(d))
    return '\n'.join(res) + '\n'
            
def sing():
    return verses(1, 12)

if __name__ == '__main__':
    print sing()
