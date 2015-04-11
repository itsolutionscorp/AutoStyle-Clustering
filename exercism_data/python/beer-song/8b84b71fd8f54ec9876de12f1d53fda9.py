__author__ = 'tracyrohlin'

def verse(n):
    bottles = "bottles"
    bottle = "bottle"
    no_more = "No more"
    one = "one"

    if n == 2:
        return "{0} {1} of beer on the wall, {0} {1} of beer.\nTake {2} down and pass it around, {3} {4} of beer on the wall.\n".format(n, bottles, one, (n-1), bottle)


    elif n == 1:
        one = "it"
        return "{0} {1} of beer on the wall, {0} {1} of beer.\nTake {2} down and pass it around, {3} {4} of beer on the wall.\n".format(n, bottle, one, no_more.lower(), bottles)

    elif n == 0:
        return "{0} {1} of beer on the wall, {2} {1} of beer.\nGo to the store and buy some more, {3} {4} of beer on the wall.\n".format(no_more, bottles, no_more.lower(), 99, bottles)

    return "{0} {1} of beer on the wall, {0} {1} of beer.\nTake one down and pass it around, {2} {3} of beer on the wall.\n".format(n, bottles, (n-1), bottles)


def song(n, d=0):
    master_list = []
    if not d:
        while n >= 0:
            master_list.append(verse(n))
            n -= 1
    while n >= d:
        master_list.append(verse(n))
        n -= 1
    return "\n".join((master_list)) + "\n"

print verse(1)
