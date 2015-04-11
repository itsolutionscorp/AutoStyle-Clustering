ORDS = "zeroth first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth".split()

GIFTS = """ twelve Drummers Drumming, eleven Pipers Piping, ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree""".split(",")


def sing():
    return verses(1,12)

def verse(v):
    gifts = ",".join(GIFTS[-v:])
    template = "On the {ordinal} day of Christmas my true love gave to me,{g}.\n"
    output = template.format(ordinal = ORDS[v], g=gifts)
    if v == 1:
        output = output.replace("and a","a")
    return output

def verses(start,end):
    return "\n".join(verse(n) for n in range(start,end+1))+"\n"
