def verse(n):
    if n == 0:
        return  "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    if n == 1:
        return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    if n == 2:
        return "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    return  "%i bottles of beer on the wall, %i bottles of beer.\nTake one down and pass it around, %i bottles of beer on the wall.\n" % (n, n, n-1)

def song(start, end=0):
    return "\n".join([verse(i) for i in range(start, end-1, -1)]) + "\n"
