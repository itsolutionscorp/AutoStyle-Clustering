'''exer house'''

SUBJECT_VERBS = [('house', 'built'),
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
                 ('horse and the hound and the horn', 'belonged to')]


def rhyme():
    '''return all verses in the nursery rhyme, that Jack built'''
    return '\n\n'.join([verse(num) for num in range(len(SUBJECT_VERBS))])

def verse(num):
    '''return the specific verse'''
    return 'This is %s' % recursiverse(num)

def recursiverse(num):
    '''silly little recursive bit'''
    subject, verb = SUBJECT_VERBS[num]
    if num:  # wind it up some more
        return 'the %s\nthat %s %s' % (subject, verb, recursiverse(num - 1))
    else:    # guard OO recursion and start the unwind
        return 'the %s that Jack %s.' % (subject, verb)

if __name__ == '__main__':
    print verse(0)
    print
    print verse(1)
