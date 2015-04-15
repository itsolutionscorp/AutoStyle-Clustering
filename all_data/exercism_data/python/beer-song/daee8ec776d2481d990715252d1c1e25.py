def verse(bottles):
    if bottles == 0:
        return ("No more bottles of beer on the wall, "
                "no more bottles of beer.\n"
                "Go to the store and buy some more, "
                "99 bottles of beer on the wall.\n")
    elif bottles == 1:
        return ("1 bottle of beer on the wall, "
                "1 bottle of beer.\n"
                "Take it down and pass it around, "
                "no more bottles of beer on the wall.\n")
    elif bottles == 2:
        return ("2 bottles of beer on the wall, "
                "2 bottles of beer.\n"
                "Take one down and pass it around, "
                "1 bottle of beer on the wall.\n")
    else:
        return ("{0} bottles of beer on the wall, "
                "{0} bottles of beer.\n"
                "Take one down and pass it around, "
                "{1} bottles of beer on the wall.\n").format(bottles, 
                                                             bottles - 1)
    raise ValueError("bottles has to be >= 0")

def song(start, end = 0):
    return "\n".join(verse(nr) for nr in range(start, end - 1, -1)) + "\n"
