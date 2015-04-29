parts = [
    ('house that Jack built', ''),
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

def verse(n):
    s = ["This is the "]
    x = list(reversed(parts[:n]))

    while len(x) > 1:
        thing, action = x.pop(0)
        s.append(thing)
        s.extend(["\nthat ", action, " the "])

    thing, action = x.pop(0)
    s.append(thing + ".")

    return ''.join(s)

def rhyme():
    return "\n\n".join(verse(i) for i in range(1, 13))
