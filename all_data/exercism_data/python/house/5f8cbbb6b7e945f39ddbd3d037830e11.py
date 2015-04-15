HOUSE_DICT = {'house that Jack built.': 'lay in', 'malt': 'ate', 'rat': 'killed', 'cat': 'worried', 'dog': 'tossed', 'cow with the crumpled horn': 'milked', 'maiden all forlorn': 'kissed', 'man all tattered and torn': 'married', 'priest all shaven and shorn': 'woke', 'rooster that crowed in the morn': 'kept', 'farmer sowing his corn': 'belonged to'}

HOUSE_LIST = ['house that Jack built.', 'malt', 'rat', 'cat', 'dog', 'cow with the crumpled horn', 'maiden all forlorn', 'man all tattered and torn', 'priest all shaven and shorn', 'rooster that crowed in the morn', 'farmer sowing his corn', 'horse and the hound and the horn']

def rhyme():
    return '\n\n'.join(verse(i) for i in xrange(len(HOUSE_LIST)))

def verse(num):
    lines = []
    for i in xrange(num+1):
        if i == 0:
            lines.append('This is the {0}'.format(HOUSE_LIST[num]))
        else:
            lines.append('that {0} the {1}'.format(HOUSE_DICT[HOUSE_LIST[num-i]],HOUSE_LIST[num-i]))
    return '\n'.join(lines)
