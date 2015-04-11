class Beer(object):
    def sing(self, first, last=0):
        return ''.join(self.verse(n)+'\n' for n in range(first,last-1,-1))

    def verse(self, n):
        if n > 0:
            return (
                '{n_bottles} of beer on the wall, {n_bottles} of beer.\n'
                'Take {pronoun} down and pass it around, '
                '{n_minus_one_bottles} of beer on the wall.\n'
                ).format(
                        n_bottles=self.bottles(n),
                        n_minus_one_bottles=self.bottles(n-1),
                        pronoun=('one' if n>1 else 'it'),
                        )
        else:
            return (
                'No more bottles of beer on the wall, '
                'no more bottles of beer.\n'
                'Go to the store and buy some more, '
                '99 bottles of beer on the wall.\n'
                )

    def bottles(self, n):
        if n > 1:
            return '{n} bottles'.format(n=n)
        elif n == 1:
            return '1 bottle'
        else:
            return 'no more bottles'
