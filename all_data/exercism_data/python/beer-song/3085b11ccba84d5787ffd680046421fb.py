class Beer(object):
    def sing(self, beer1, beer2=0):
        verses = ''
        for i in reversed(range(beer2, beer1 + 1)):
            verses += self.verse(i) + '\n'

        return verses

    def verse(self, i):
        return ''.join([
            "%s of beer on the wall, " % self.totalbottles(i).capitalize(),
            "%s of beer.\n" % self.totalbottles(i),
            self.storebound(i),
            self.newbottle(i),
        ])

    def storebound(self, current_verse):
        if current_verse == 0:
            return "Go to the store and buy some more, "
        else:
            return "Take %s down and pass it around, " % (
                "one" if current_verse > 1 else "it"
            )

    def newbottle(self, current_verse):
        return "%s of beer on the wall.\n" % self.totalbottles(self.next_verse(current_verse))

    def totalbottles(self, i):
        if i == 0:
            return 'no more bottles'
        if i == 1:
            return '1 bottle'
        else:
            return '%d bottles' % i

    def next_verse(self, current_verse):
        return current_verse - 1 if current_verse > 0 else 99
