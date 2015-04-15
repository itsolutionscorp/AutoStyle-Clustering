__author__ = 'emiller42'

def song(begin, end=0):
    output = ""
    for bottle in range(begin, end-1, -1):
        output += verse(bottle) + "\n"
    return output


def verse(bottles):

    def word_bottles(bottles):
        if bottles == 0:
            return "No more bottles "
        if bottles == 1:
            return "1 bottle "
        return str(bottles) + " bottles "

    word = word_bottles(bottles)
    output = word + "of beer on the wall, " + word.lower() + "of beer.\n"

    if bottles == 0:
        output += "Go to the store and buy some more, "
        bottles = 99
    else:
        output += "Take "
        if bottles == 1:
            output += "it "
        else:
            output += "one "
        output += "down and pass it around, "
        bottles -= 1

    word = word_bottles(bottles)

    output += word.lower() + "of beer on the wall.\n"

    return output
