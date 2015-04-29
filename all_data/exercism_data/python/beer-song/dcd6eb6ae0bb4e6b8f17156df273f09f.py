def action_text(n):
    if n == 0:
        return "Go to the store and buy some more"

    if n == 1:
        return "Take it down and pass it around"

    return "Take one down and pass it around"


def bottles_text(n):
    if n == 0:
        return "No more bottles"

    if n == 1:
        return "1 bottle"

    return "{} bottles".format(n)


class Beer(object):
    def verse(self, n):
        if n == 0:
            next_n = 99
        else:
            next_n = n - 1

        b = bottles_text(n)
        return "{bottles} of beer on the wall, {bottles_lower} of beer.\n" \
            "{action}, {next_bottles} of beer on the wall.\n".format(
                bottles=b,
                bottles_lower=b.lower(),
                action=action_text(n),
                next_bottles=bottles_text(next_n).lower(),
            )

    def sing(self, from_, to=0):
        return "".join(self.verse(i) + "\n" for i in range(from_, to - 1, -1))
