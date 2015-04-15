def verse(n):
    """Returns a string representing the nth verse of the Beer Song"""
    if n <= 0:
        return  "No more bottles of beer on the wall, " + \
                "no more bottles of beer.\n" + \
                "Go to the store and buy some more, " + \
                "99 bottles of beer on the wall.\n"
    elif n == 1:
        return "1 bottle of beer on the wall, " + \
               "1 bottle of beer.\n" + \
               "Take it down and pass it around, " + \
               "no more bottles of beer on the wall.\n"
    else:
        return  str(n) + " bottles of beer on the wall, " + \
                str(n) + " bottles of beer.\n" + \
                _second_half_of_verse(n)            

def _second_half_of_verse(n):
    """Returns the second half of the nth verse of the Beer Song,
    ensuring the number cases match correctly
    """
    verse = "Take one down and pass it around, "
    if n == 2:
        verse += "1 bottle of beer on the wall.\n"
    else:
        verse += str(n-1) + " bottles of beer on the wall.\n"
    return verse    

def song(start=99, stop=0):
    """Returns the Beer Song starting a verse n"""
    song = ''
    for i in range(start, stop-1, -1):
        song += verse(i) + '\n'
    return song
