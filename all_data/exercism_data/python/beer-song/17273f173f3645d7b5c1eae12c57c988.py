def song(start, end=0):
    words = ''
    for i in range(start,end-1,-1):
        words += verse(i) + '\n'
    return words

def verse(beers):
    if beers == 0:
        return ("No more bottles of beer on the wall, no more bottles of beer.\n"
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
    elif beers == 1:
        return ("1 bottle of beer on the wall, 1 bottle of beer.\n"
            "Take it down and pass it around, no more bottles of beer on the wall.\n")

    else:
        if beers == 2:
            plural = ''
        else:
            plural = 's'

    return ("{0} bottles of beer on the wall, {0} bottles of beer.\n"
    "Take one down and pass it around, {1} bottle{2} of beer on the wall.\n").format(beers, beers-1, plural)
