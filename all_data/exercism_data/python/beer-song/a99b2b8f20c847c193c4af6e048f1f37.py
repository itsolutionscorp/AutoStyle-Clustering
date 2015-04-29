# My implementation: Isn't it beautiful?!
def bottles(n):
    return '{} bottle{} of beer'.format('no more' if n == 0 else str(n),
            '' if n == 1 else 's')
def verse(n):
    otw = ' on the wall'
    b = bottles(n)

    v = b + otw + ', ' + b
    if n == 0:
        v = v[0].upper() + v[1:]
        v += '.\nGo to the store and buy some more, '
    else:
        v += '.\nTake {} down and pass it around, '.format(
                'it' if n == 1 else 'one')
    v += bottles((n - 1)%100) + otw + '.\n'
    return v
def sing(b, a = 0):
    s = ''
    for i in range(b, a - 1, -1):
        s += verse(i) + '\n'
    return s

# Satisfy the test writer's class fetish.
class Beer:
    def verse(self, *args):
        return verse(*args)
    def sing(self, *args):
        return sing(*args)
