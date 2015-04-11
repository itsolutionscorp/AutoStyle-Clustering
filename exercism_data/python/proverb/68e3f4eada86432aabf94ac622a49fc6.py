def proverb(items, qualifier=''):
    """
        For want of a {A} the {B} was lost.
        For want of a {B} the {C} was lost.
        And all for the want of a {Q} {A}.
    """
    if qualifier != '':
        qualifier = qualifier + ' '
    proverb = ["For want of a {0} the {1} was lost.".format(x,y) for (x,y) in zip(items[0:len(items)-1], items[1:])]
    proverb.append("And all for the want of a {0}{1}.".format(qualifier, items[0]))
    return "\n".join(proverb)
