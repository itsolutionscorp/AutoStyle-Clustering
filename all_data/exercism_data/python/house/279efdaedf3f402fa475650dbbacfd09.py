ACTORS = [
    ('house that Jack built.', 'lay in'),
    ('malt', 'ate'),
    ('rat', 'killed'),
    ('cat', 'worried'),
    ('dog', 'tossed'),     
    ('cow with the crumpled horn', 'milked'),
    ('maiden all forlorn', 'kissed'),      
    ('man all tattered and torn', 'married'),      
    ('priest all shaven and shorn', 'woke'),     
    ('rooster that crowed in the morn', 'kept'),        
    ('farmer sowing his corn', 'belonged to'),        
    ('horse and the hound and the horn', None)
]

def rhyme():
    return "\n\n".join(
        "\n".join(verse(nr)) for nr in range(len(ACTORS))
    )

def verse(nr):
    actor, _ = ACTORS[nr]
    lines = ["This is the %s" % actor]
    for nr in range(nr-1, -1, -1):
       target, action = ACTORS[nr]
       lines.append("that %s the %s" % (action, target))
    return lines
