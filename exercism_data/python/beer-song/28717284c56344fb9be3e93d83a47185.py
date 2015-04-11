import re


class Beer(object):

    def __init__(self):
        pass

    def verse(self, n):
        s1 = "%s bottles of beer on the wall, %s bottles of beer.\n"
        s2 = ("Take one down and pass it around, "
              "%s bottles of beer on the wall.\n")

        if n > 1 and n < 100:
            sentence = s1 % (n, n) + s2 % (n-1)
        elif n >= 100:
            sentence = None
        elif n == 1:
            sentence = s1 % (n, n) + s2 % "no more"
        elif n == 0:
            sentence = s1 % ("No more", "no more") + \
                       "Go to the store and buy some more, " + \
                       "99 bottles of beer on the wall.\n"

        sentence = re.sub("1 bottles", "1 bottle", sentence)
        if "1 bottle" in sentence and "2 bottles" not in sentence:
            sentence = re.sub("one down", "it down", sentence)

        return sentence

    def sing(self, m, n=0):

        verses = ''
        for i in range(m, n-1, -1):
            verses = verses + self.verse(i) + '\n'

        return verses
