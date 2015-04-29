#1 hour 15 minutes with minor distractions

def sing():
    return verses(1,12)

def verse(i):
    lines = []
    lines.append("On the %s day of Christmas my true love gave to me" % days[i])
    lines += chorus(i)
    lines.append(ending(i))
    lines = ', '.join(lines)
    return lines + "\n"

def verses(start,stop):
    return "\n".join([verse(i) for i in range(start,stop+1)]) + "\n"

def chorus(i):
    present = i
    chorus = []
    while (present > 1):
        chorus.append(lines[present])
        present = present - 1
    return chorus

def ending(i):
    if i == 1:
        return lines[1]
    else:
        return "and " + lines[1] 
    

lines = [
        'NAN',
        'a Partridge in a Pear Tree.', 
        'two Turtle Doves', 
        'three French Hens', 
        'four Calling Birds', 
        'five Gold Rings',
        'six Geese-a-Laying', 
        'seven Swans-a-Swimming', 
        'eight Maids-a-Milking', 
        'nine Ladies Dancing', 
        'ten Lords-a-Leaping', 
        'eleven Pipers Piping', 
        'twelve Drummers Drumming'
        ]

days = [
        'NAN',
        'first', 
        'second', 
        'third', 
        'fourth',
        'fifth', 
        'sixth', 
        'seventh', 
        'eighth', 
        'ninth', 
        'tenth', 
        'eleventh', 
        'twelfth'
        ]
