def verse(n):
    if n == 0:
        return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elif n == 1:
        return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else:
        return str(n) + " bottles of beer on the wall, "+str(n)+" bottles of beer.\nTake one down and pass it around, "+str(n-1)+" bottle"+("s" if n > 2 else '')+" of beer on the wall.\n"
    

def song(n, d = 0):
    return '\n'.join([verse(i) for i in range(n,d-1,-1)]) + '\n'
