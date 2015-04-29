"""A program that prints the nursery rhyme "The house that Jack built"."""


from functools import lru_cache


COMPONENTS = (('house', 'Jack built'),
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
              ('horse and the hound and the horn', 'belonged to'))


def rhyme():
    """Return the entire nursery rhyme."""
    return '\n\n'.join([verse(n) for n in range(12)])


def verse(n):
    """Return the nth verse where 0 <= n <= 10"""
    return 'This is ' + verse_core(n)


@lru_cache()
def verse_core(n):
    """Return the core, i.e. everything but 'This is ', for a verse."""
    thing, action = COMPONENTS[n]
    if n == 0:
        return 'the {} that {}.'.format(thing, action)
    return 'the {}\nthat {} {}'.format(thing, action, verse_core(n - 1))


if __name__ == '__main__':
    print('\n' + rhyme() + '\n')
