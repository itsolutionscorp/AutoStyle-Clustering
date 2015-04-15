def song(nr_from, nr_to = 0):
    """Generate the lyrics for the beer song.
    Parameter nr_from specifies the number of beer bottles to start with.
    Parameter nr_to (optional) defines until what number of bottles to
    generate the song lyrics. When nr_to is omitted, then the lyrics will
    be generated until the end of the song.
    """
    return "".join(_generate_lyrics(nr_from, nr_to))

def verse(nr):
    """Generate the lyrics for a single verse of the beer song.
    Parameter nr specifies the number of beer bottles still on the wall.
    """
    return "".join(_generate_lyrics(nr, nr))

def _generate_lyrics(nr_from, nr_to):
    for nr in range(nr_from, nr_to - 1, -1):
        yield _first_line_of_verse(nr)
        yield _second_line_of_verse(nr)
        if nr_from != nr_to: yield "\n"

def _first_line_of_verse(nr):
    bob = _bottles_of_beer(nr)
    return "%s %s, %s.\n" % (bob.capitalize(), _where(), bob)

def _second_line_of_verse(nr):
    if nr: return _second_line_of_normal_verse(nr)
    return _second_line_of_last_verse()

def _second_line_of_normal_verse(nr):
    bob = _bottles_of_beer(nr - 1)
    it_or_one = "it" if nr == 1 else "one"
    return "Take %s down and pass it around, %s %s.\n" \
           % (it_or_one, bob, _where())

def _second_line_of_last_verse():
    bob = _bottles_of_beer(99)
    return "Go to the store and buy some more, %s %s.\n" % (bob, _where())

def _bottles_of_beer(nr):
    bottles = "bottle%s of beer" % ("s" if nr != 1 else "")
    howmany = "no more" if nr == 0 else nr
    return "%s %s" % (howmany, bottles)

def _where():
    return "on the wall"
