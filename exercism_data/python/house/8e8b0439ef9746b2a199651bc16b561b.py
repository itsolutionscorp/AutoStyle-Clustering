def rhyme():
    prefix = 'This is the '
    verse = 'house that Jack built.'
    phrase = '%s\nthat %s the '
    ret = prefix + verse
    arr = [('malt', 'lay in'),('rat','ate'),('cat','killed'),
           ('dog','worried'),('cow with the crumpled horn','tossed'),
           ('maiden all forlorn','milked'),
           ('man all tattered and torn','kissed'),
           ('priest all shaven and shorn','married'),
           ('rooster that crowed in the morn','woke'),
           ('farmer sowing his corn','kept'),
           ('horse and the hound and the horn','belonged to')]

    for t in arr:
        verse = phrase % t + verse
        ret += '\n\n' + prefix + verse

    return ret
