"""This is the House that Jack Built"""

from collections import namedtuple


Phrase = namedtuple("Phrase", ["verb", "noun"])

phrases = [
    Phrase("lay in", "the house that Jack built"),
    Phrase("ate", "the malt"),
    Phrase("killed", "the rat"),
    Phrase("worried", "the cat"),
    Phrase("tossed", "the dog"),
    Phrase("milked", "the cow with the crumpled horn"),
    Phrase("kissed", "the maiden all forlorn"),
    Phrase("married", "the man all tattered and torn"),
    Phrase("woke", "the priest all shaven and shorn"),
    Phrase("kept", "the rooster that crowed in the morn"),
    Phrase("belonged to", "the farmer sowing his corn"),
    Phrase(None, "the horse and the hound and the horn")
]


def rhyme():
    return "\n\n".join(stanza(i) for i in xrange(len(phrases)))


def stanza(nth):
    lines = ["This is {}".format(phrases[nth].noun)]
    lines.extend(
        "that {} {}".format(phrases[i].verb, phrases[i].noun)
        for i in xrange(nth - 1, -1, -1)
    )
    lines[-1] += "."
    return "\n".join(lines)
