class Beer(object):
    def verse(self, verse):
        if verse == 0:
            bottles = "No more bottles"
            action = "Go to the store and buy some more"
            next_bottles = "99 bottles"
        elif verse == 1:
            bottles = "1 bottle"
            action = "Take it down and pass it around"
            next_bottles = "no more bottles"
        else:
            bottles = "%d bottles" % verse
            action = "Take one down and pass it around"
            if verse == 2:
                next_bottles = "1 bottle"
            else:
                next_bottles = "%d bottles" % (verse - 1)
        verse_text = "%s of beer on the wall, %s of beer.\n" % (bottles, bottles.lower()) + \
                          "%s, %s of beer on the wall.\n" % (action, next_bottles)
        return verse_text


    def sing(self, start, end=0):
        song = ""
        for i in range(start, end-1, -1):
            song += self.verse(i) + "\n"
        return song
