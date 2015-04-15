import re


_last_line = '''This is the horse and the hound and the horn
that belonged to the farmer sowing his corn
that kept the rooster that crowed in the morn
that woke the priest all shaven and shorn
that married the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog
that worried the cat
that killed the rat
that ate the malt
that lay in the house that Jack built.'''


def rhyme():
    lines = _last_line.splitlines()
    return '\n\n'.join(re.sub(r'.*?the', 'This is the', '\n'.join(lines[i:]), count=1)
                       for i in range(len(lines) - 1, -1, -1))
