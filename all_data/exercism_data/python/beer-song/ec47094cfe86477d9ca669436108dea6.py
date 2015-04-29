class Beer(object):
    def _action(self, n):
        if n == 0:
            return "Go to the store and buy some more"

        if n == 1:
            return "Take it down and pass it around"

        return "Take one down and pass it around"

    def _bottles(self, n):
        if n == 0:
            return "No more bottles"

        if n == 1:
            return "1 bottle"

        return "%d bottles" % (n,)

    def _next_of(self, n):
        if n == 0:
            return 99

        return n - 1

    def verse(self, n):
        return "%(bottles)s of beer on the wall, %(bottles_lower)s of beer.\n" \
            "%(action)s, %(next_bottles)s of beer on the wall.\n" % {
                'bottles': self._bottles(n),
                'bottles_lower': self._bottles(n).lower(),
                'action': self._action(n),
                'next_bottles': self._bottles(self._next_of(n)).lower(),
            }

    def sing(self, from_, to=0):
        return "".join(self.verse(i) + "\n" for i in range(from_, to - 1, -1))
