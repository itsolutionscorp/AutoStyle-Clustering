def _n_bottle(n):
    if n == 1:
        bottle = 'bottle'
    else:
        bottle = 'bottles'

    if n == 0:
        number = 'No more'
    else:
        number = str(n)

    return '{0} {1} of beer'.format(number, bottle)


def _bottle_action(n):
    if n == 0:
        return 'Go to the store and buy some more'

    if n == 1:
        bottle_pronoun = 'it'
    else:
        bottle_pronoun = 'one'

    return "Take {0} down and pass it around".format(bottle_pronoun)


class Beer(object):
    def verse(self, n):
        return ("{0} on the wall, {1}.\n"
                "{2}, {3} on the wall.\n").format(
                    _n_bottle(n),
                    _n_bottle(n).lower(),
                    _bottle_action(n),
                    _n_bottle((n -1) % 100).lower()
                )

    def sing(self, from_, to=0):
        return ''.join(self.verse(i)+'\n' for i in xrange(from_, to-1, -1))
