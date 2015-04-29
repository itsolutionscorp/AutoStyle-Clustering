objects = [ 'house that Jack built.\n',         \
            'malt', 'rat', 'cat', 'dog',        \
            'cow with the crumpled horn',       \
            'maiden all forlorn',               \
            'man all tattered and torn',        \
            'priest all shaven and shorn',      \
            'rooster that crowed in the morn',  \
            'farmer sowing his corn',           \
            'horse and the hound and the horn'  ]
            
verbs =   [ '', 'lay in', 'ate', 'killed',      \
            'worried', 'tossed', 'milked',      \
            'kissed', 'married', 'woke',        \
            'kept', 'belonged to'               ]


def rhyme():
    r = ''
    for obj_i, obj in enumerate(objects):
        r += 'This is the {0}\n'.format(obj)
        for verb_i in range(obj_i, 0, -1):
            r += 'that {0} the {1}\n'.format(verbs[verb_i], objects[verb_i-1])
    return r.strip()
