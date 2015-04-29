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

def rhyme():
    the_rhyme, verse = '', 'the house that Jack built.'
    for (noun, modifier) in PHRASES:
        the_rhyme += 'This is ' + verse + '\n\n'
        verse = 'the ' + noun + '\n' + \
                'that ' + modifier + ' ' \
                + verse
    return the_rhyme + 'This is ' + verse
