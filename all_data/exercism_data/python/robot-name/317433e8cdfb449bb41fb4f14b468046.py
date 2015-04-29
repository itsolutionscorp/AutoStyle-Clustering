import random

class Robot(object):
    _seq = None

    @classmethod
    def name_code(cls):
        """ for each char: (number of possible values, int-to-chr conversion func) """
        toalpha = lambda x: chr(x+65)
        return ( (26, toalpha), 
                 (26, toalpha),
                 (10, str),
                 (10, str),
                 (10, str), )

    
    @classmethod
    def _sequence(cls):
        if cls._seq == None:
            #multiply bases together for max number of names
            maxlength = reduce( lambda a, (k,v) : a * k, cls.name_code(), 1)
            cls._seq = RandomSequence(maxlength)

        return cls._seq

    @classmethod
    def _newkey(cls):
        return cls._sequence().next()


    @classmethod
    def _gen_name(cls):
        key = cls._newkey()

        name_chars = []

        for base, f in reversed(cls.name_code()):
            # mod and divide starting at lowest base
            # and call each base's conversion function
            name_chars.append( f(key % base) )
            key = key / base

        return "".join(reversed(name_chars) )


    def __init__(self):
        self.reset()


    def reset(self):
        self.name = self.__class__._gen_name()

   
class RandomSequence(object):
    """ Generates a random sequence of a given length with no repeats using
        Fisher–Yates shuffle
        (http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle) """

    def __init__(self, maxlength):
        self.maxlength = maxlength

        def swap(l, i, j): 
            t = l[i]
            l[i] = l[j]
            l[j] = t

        self._seq = [x for x in xrange(maxlength)]

        for i in xrange(len(self._seq) - 1, 0, -1):
            swap(self._seq, i, random.randint(0, i-1) )

        self._iter = iter(self._seq)

    def next(self):
        return next(self._iter)
