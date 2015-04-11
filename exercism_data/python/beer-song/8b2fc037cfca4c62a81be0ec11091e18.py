def song(start, end=0):
    lyrics = ""

    for i in range(start, end - 1, -1):
        lyrics += verse(i) + "\n"

    return lyrics

def verse(num):
    verse = fmt_beer(num, True) + " on the wall, " + fmt_beer(num) + ".\n"
    if num == 0:
        verse += "Go to the store and buy some more, " + fmt_beer(99) + " on the wall.\n"
    else:
        verse += "Take " + ("it" if num == 1 else "one") + " down and pass it around, " + fmt_beer(num - 1) + " on the wall.\n"

    return verse

def fmt_beer(count, capitalize=False):
    if count == 0:
        count = ("N" if capitalize else "n") + "o more"

    lead = str(count) + " bottle"
    if count != 1:
        lead += "s"

    return lead + " of beer"
