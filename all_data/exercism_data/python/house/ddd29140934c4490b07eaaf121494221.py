dc = {
    0: 'This is',
    1: 'the malt\nthat lay in',
    2: 'the rat\nthat ate',
    3: 'the cat\nthat killed',
    4: 'the dog\nthat worried',
    5: 'the cow with the crumpled horn\nthat tossed',
    6: 'the maiden all forlorn\nthat milked',
    7: 'the man all tattered and torn\nthat kissed',
    8: 'the priest all shaven and shorn\nthat married',
    9: 'the rooster that crowed in the morn\nthat woke',
    10: 'the farmer sowing his corn\nthat kept',
    11: 'the horse and the hound and the horn\nthat belonged to',
    12: 'the house that Jack built.\n\n'
    }


def rhyme():
    stavaya = ''
    for i in range(0, 12):
        stavaya += rec(i)
    return stavaya


def rec(lim):
    tmpa = dc[0]
    for j in range(lim, 0, -1):
        tmpa += ' ' + (dc[j])
    if lim < 11:
        return tmpa + ' ' + dc[12]
    else:
        return tmpa + ' ' + dc[12][:-2]
