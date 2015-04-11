class Beer(object):

    def __init__(self):
        pass

    def get_action(self, count):
        if count == 0:
            action = "Go to the store and buy some more"
        elif count == 1:
            action = "Take it down and pass it around"
        else:
            action = "Take one down and pass it around"
        return action

    def get_bottles(self, count):
        if count == 0:
            bc = "no more bottles of beer"
        elif count == 1:
            bc = "1 bottle of beer"
        else:
            bc = str(count) + " bottles of beer"
        return bc

    def verse(self, count):
        action = self.get_action(count)
        this_count = self. get_bottles(count)

        if count == 0:
            next_count = self.get_bottles(99)
        else:
            next_count = self.get_bottles(count-1)

        v = ("%s on the wall, %s.\n%s, %s on the wall.\n" %
             (this_count.capitalize(), this_count, action, next_count))

        return v

    def sing(self, first_count, last_count=0):
        verses = []
        for count in xrange(first_count, last_count, -1):
           verses.append(self.verse(count))
        verses.append(self.verse(last_count))
        return "\n".join(verses) + "\n"
