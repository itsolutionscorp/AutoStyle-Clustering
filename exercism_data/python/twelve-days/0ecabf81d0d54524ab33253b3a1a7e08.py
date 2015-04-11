presents=[ "a Partridge in a Pear Tree.\n","two Turtle Doves", "three French Hens",
"four Calling Birds", "five Gold Rings", "six Geese-a-Laying", "seven Swans-a-Swimming",
"eight Maids-a-Milking", "nine Ladies Dancing", "ten Lords-a-Leaping",
"eleven Pipers Piping", "twelve Drummers Drumming"]

ordinals = ["first","second","third","fourth","fifth","sixth","seventh","eigth","ninth",
"tenth","eleventh","twelfth"]

def sing():
     
    return verses(1,12)


def verse(n):

    lyrics="On the {0} day of Christmas my true love gave to me, ".format(ordinals[n-1])
    lyrics+=", ".join(presents[n-1:0:-1])
    if (n!=1): lyrics+=", and "
    lyrics+=presents[0]
    return lyrics


def verses(start,finish):

    return "\n".join([verse(i) for i in range(start,finish+1)])+"\n"
    

def main():
    """ Test function"""

    print(verse(8))


if __name__=="__main__":
    main()
