song = [
        ('first' , 'a Partridge'),
        ('second' , 'two Turtle Doves'),
        ('third' , 'three French Hens'),
        ('fourth' , 'four Calling Birds'),
        ('fifth' , 'five Gold Rings'),
        ('sixth' , 'six Geese-a-Laying'),
        ('seventh' , 'seven Swans-a-Swimming'),
        ('eighth' , 'eight Maids-a-Milking'),
        ('ninth' , 'nine Ladies Dancing'),
        ('tenth' , 'ten Lords-a-Leaping'),
        ('eleventh' , 'eleven Pipers Piping'),
        ('twelfth' , 'twelve Drummers Drumming')
        ]



def sing():
    return verses(1, 12)
    """
    rest = ""
    phrase = "On the {day} of Christmas my true love gave to me, {what} in a Pear Tree.\n\n"
    total = ""
    for i in range(len(song)):
        if i == 0:
            total += phrase.format(day=song[i][0], what=song[i][1])
            rest = ", and %s" % song[i][1]
        else:
            total += phrase.format(day=song[i][0], what=song[i][1] + rest)
            rest = ", %s%s" % (song[i][1], rest)
    """
            

def verse(n):
    rest = ""
    phrase = "On the {day} day of Christmas my true love gave to me, {what} in a Pear Tree.\n"
    total = ""
    for i in range(n-1):
        if i == 0:
            rest = ", and %s" % song[i][1]
        else:
            rest = ", %s%s" % (song[i][1], rest)
    return phrase.format(day=song[n-1][0], what=song[n-1][1] + rest)

def verses(n, m):
    rest = ""
    phrase = "On the {day} day of Christmas my true love gave to me, {what} in a Pear Tree.\n\n"
    total = ""
    for i in range(len(song)):
        if i == 0:
            if i in range(n-1,m):
                total += phrase.format(day=song[i][0], what=song[i][1])
            rest = ", and %s" % song[i][1]
        else:
            if i in range(n-1,m):
                total += phrase.format(day=song[i][0], what=song[i][1] + rest)
            rest = ", %s%s" % (song[i][1], rest)
    return total


#print(sing())
#print(verses(1,3))
#print(verse(3))
