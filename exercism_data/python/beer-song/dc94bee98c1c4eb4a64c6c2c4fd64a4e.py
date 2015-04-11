class Beer:
    def __init__(self):
        pass

    def verse(self, num):
        if num == 0:
            return "No more bottles of beer on the wall, no more bottles of beer.\n" \
                    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        elif num == 1:
            return "1 bottle of beer on the wall, 1 bottle of beer.\n" \
                    "Take it down and pass it around, no more bottles of beer on the wall.\n"
        elif num == 2:
            return "2 bottles of beer on the wall, 2 bottles of beer.\n" \
                    "Take one down and pass it around, 1 bottle of beer on the wall.\n"
        else:
            return "%d bottles of beer on the wall, %d bottles of beer.\n" \
            "Take one down and pass it around, %d bottles of beer on the wall.\n" % (num, num, num-1)

    def sing(self, start, end=0):
        result = ""
        while start >= end:
            result += self.verse(start)
            result += "\n"
            start -= 1
        return result
