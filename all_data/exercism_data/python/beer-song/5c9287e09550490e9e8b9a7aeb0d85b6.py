def song(start, stop=0):

    songtxt = ""
    for i in range(start, stop - 1, -1):
        songtxt += verse(i) + "\n"
    return songtxt


def verse(vNum):

    if vNum >= 2:
        firstLine = "%s bottles of beer on the wall, %s bottles of beer.\n" % (str(vNum), str(vNum))
    elif vNum == 1:
        firstLine = "1 bottle of beer on the wall, 1 bottle of beer.\n"
    else:
        firstLine = "No more bottles of beer on the wall, no more bottles of beer.\n"

    if vNum >= 3:
        secondLine = "Take one down and pass it around, %s bottles of beer on the wall.\n" % (str(vNum-1))
    elif vNum == 2:
        secondLine = "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    elif vNum == 1:
        secondLine = "Take it down and pass it around, no more bottles of beer on the wall.\n"
    else:
        secondLine = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

    return firstLine + secondLine
