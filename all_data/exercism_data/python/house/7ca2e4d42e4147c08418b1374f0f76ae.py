PAIRS = [
    ('malt', 'lay in'),
    ('rat', 'ate'),
    ('cat', 'killed'),
    ('dog', 'worried'),
    ('cow with the crumpled horn', 'tossed'),
    ('maiden all forlorn', 'milked'),
    ('man all tattered and torn', 'kissed'),
    ('priest all shaven and shorn', 'married'),
    ('rooster that crowed in the morn', 'woke'),
    ('farmer sowing his corn', 'kept'),
    ('horse and the hound and the horn', 'belonged to'),
]


def _verse(n):
    verse = ['{0}\nthat {1} the '.format(noun, verb) for noun, verb in reversed(PAIRS[:n + 1])]
    verse[0] = 'This is the ' + verse[0]
    verse[-1] = verse[-1] + 'house that Jack built.'
    return ''.join(verse)


def rhyme():
    lines = ["This is the house that Jack built."]
    lines.extend([_verse(n) for n in range(len(PAIRS))])
    return '\n\n'.join(lines)
