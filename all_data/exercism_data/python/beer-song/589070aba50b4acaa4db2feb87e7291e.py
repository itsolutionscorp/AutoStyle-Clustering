def bottle_count(count):
    if count is 0:
        bottles = "No more bottles"
    elif count is 1:
        bottles = "1 bottle"
    else:
        bottles = str(count) + " bottles"

    return '%s %s' % (bottles, "of beer")


class Verse(object):
    ending = "on the wall"

    def __init__(self, num):
        self.verse = num
        self.nextVerse = num - 1 if num > 0 else 99

    def count(self):
        return bottle_count(self.verse)

    def action(self):
        if self.verse is 0:
            return "Go to the store and buy some more,"
        elif self.verse is 1:
            return "Take it down and pass it around,"
        else:
            return "Take one down and pass it around,"

    def bottle_verse(self):
        count = self.count()
        return '%s %s, %s' % (count, self.ending, count.lower())

    def action_verse(self):
        return '%s %s %s' % (
            self.action(), bottle_count(self.nextVerse).lower(), self.ending)

    def __str__(self):
        return "%s.\n%s.\n" % (self.bottle_verse(), self.action_verse())


class Beer(object):
    @classmethod
    def verse(cls, num):
        return str(Verse(num))

    @classmethod
    def sing(cls, start, end=0):
        return "\n".join(
            [str(Verse(verse)) for verse in range(start, end-1, -1)]) + "\n"
