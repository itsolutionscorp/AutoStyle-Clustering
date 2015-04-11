PHRASES = [ ('malt', 'lay in'),
            ('rat', 'ate'),
            ('cat', 'killed'),
            ('dog', 'worried'),
            ('cow with the crumpled horn', 'tossed'),
            ('maiden all forlorn', 'milked'),
            ('man all tattered and torn', 'kissed'),
            ('priest all shaven and shorn', 'married'),
            ('rooster that crowed in the morn', 'woke'),
            ('farmer sowing his corn', 'kept'),
            ('horse and the hound and the horn', 'belonged to')
          ]

def _verse(n):
    return "This is the " + \
           "".join(PHRASES[i][0] + "\nthat " + \
                   PHRASES[i][1] + " the " \
                   for i in range(n-1,-1,-1)) + \
           "house that Jack built."

def rhyme():
    return "\n\n".join(_verse(i) for i in range(12))
