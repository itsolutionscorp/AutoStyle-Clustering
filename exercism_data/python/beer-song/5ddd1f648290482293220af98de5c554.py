def song(i, j=0):
    return '\n'.join(verse(i) for i in range(i,j-1,-1)) + '\n'

def verse(i):
    template = ('{} bottle{} of beer on the wall, {} bottle{} of beer.\n'
                '{}, {} bottle{} of beer on the wall.\n')
    if i == 0:
        return template.format('No more', 's', 'no more', 's',
                               'Go to the store and buy some more', 99, 's')
    else:
        return template.format(i, '' if i==1 else 's',
                               i, '' if i==1 else 's',
                               'Take {} down and pass it around'.format('it' if i==1 else 'one'),
                               i-1 if i-1 else 'no more',
                               '' if i==2 else 's')
