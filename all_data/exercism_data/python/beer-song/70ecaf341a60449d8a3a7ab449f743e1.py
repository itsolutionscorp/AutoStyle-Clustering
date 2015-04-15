def verse(num):
    if num == 0:
        return ("No more bottles of beer on the wall, no more bottles of beer.\n"
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
    elif num == 1:
        return ("1 bottle of beer on the wall, 1 bottle of beer.\n"
                "Take it down and pass it around, no more bottles of beer on the wall.\n")
    elif num == 2:
        return ("2 bottles of beer on the wall, 2 bottles of beer.\n"
                "Take one down and pass it around, 1 bottle of beer on the wall.\n")
    else:
        return ("{0} bottles of beer on the wall, {1} bottles of beer.\n"
                "Take one down and pass it around, {2} bottles of beer on the wall.\n".format(num, num, num-1))
    
def song(start, finish=0):
    verse_list = []
    for i in reversed(range(finish, start+1)):
        verse_list.append(verse(i))
    return '\n'.join(verse_list+[''])
