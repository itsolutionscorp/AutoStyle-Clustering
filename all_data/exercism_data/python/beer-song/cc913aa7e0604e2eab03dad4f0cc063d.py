def song(nr_from, nr_to = 0):
    return "".join(_generate_lyrics(nr_from, nr_to))

def verse(nr):
    return "".join(_generate_lyrics(nr, nr))

def _generate_lyrics(nr_from, nr_to):
    for nr in range(nr_from, nr_to - 1, -1):
        yield first_line_of_verse(nr)
        yield second_line_of_verse(nr)
        if nr_from != nr_to: yield "\n"

def first_line_of_verse(nr):
    bob = bottles_of_beer(nr)
    return "%s %s, %s.\n" % (bob.capitalize(), where(), bob)

def second_line_of_verse(nr):
    if nr == 0:
        bob = bottles_of_beer(99)
        return "Go to the store and buy some more, %s %s.\n" % (bob, where())
    else:
        bob = bottles_of_beer(nr - 1)
        one = "it" if nr == 1 else "one"
        return "Take %s down and pass it around, %s %s.\n" % (one, bob, where())

def bottles_of_beer(nr):
    bottles = "bottle%s of beer" % ("s" if nr != 1 else "")
    howmany = "no more" if nr == 0 else nr
    return "%s %s" % (howmany, bottles)

def where():
    return "on the wall"
