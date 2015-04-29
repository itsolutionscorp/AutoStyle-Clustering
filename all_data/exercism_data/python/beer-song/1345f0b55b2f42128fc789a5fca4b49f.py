class Beer():
    def __init__(self):
        self._rules = {}
        self._rules[0] = "No more bottles of beer on the wall, no more bottles of beer.\n" \
            "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        self._rules[1] = "1 bottle of beer on the wall, 1 bottle of beer.\n" \
            "Take it down and pass it around, no more bottles of beer on the wall.\n"
        self._rules[2] = "2 bottles of beer on the wall, 2 bottles of beer.\n" \
            "Take one down and pass it around, 1 bottle of beer on the wall.\n"

    def verse(self, number):
        if number > 2:
            return self._get_dynamic_verse(number)
        else:
            return self._rules[number]

    def sing(self, start, end=0):
        result = ""
        for i in range(start, end-1, -1):
            result += self.verse(i) + "\n"

        return result


    def _get_dynamic_verse(self, number):
        return "%d bottles of beer on the wall, %d bottles of beer.\n" \
            "Take one down and pass it around, %d bottles of beer on the wall.\n" % (number, number, number-1)
