def verse(number):
    it = lambda n: "it" if n == 1 else "one"
    suffix = lambda n: "no more bottles" if n < 1 else \
                       "1 bottle" if n < 2 else str(n) + " bottles"

    if number == 0:
        return "No more bottles of beer on the wall, no more bottles of beer.\n" \
               "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

    verse = "{0} of beer on the wall, {0} of beer.\n" \
            "Take {2} down and pass it around, {1} of beer on the wall.\n"

    return verse.format(suffix(number), suffix(number-1), it(number))


def song(start, end=0):
    return "\n".join([verse(number)
                     for number in range(start, end - 1, -1)]) + "\n"
