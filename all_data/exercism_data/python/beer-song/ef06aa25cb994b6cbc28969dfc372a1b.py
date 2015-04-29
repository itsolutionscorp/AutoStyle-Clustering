def song(start, end=0):
    return '\n'.join([verse(bottle) for bottle in range(start,end-1,-1)])+'\n'

def verse(bottle):
    result = (str(bottle)+" bottles of beer on the wall, "+
              str(bottle)+" bottles of beer.\nTake one down and pass it around, "+
              str(bottle-1)+" bottles of beer on the wall.\n")
    if bottle < 3:
        if bottle == 2:
            result = result[:92]+result[93:]
        elif bottle == 1:
            result = ("1 bottle of beer on the wall, 1 bottle of beer.\n"+
                      "Take it down and pass it around, no more bottles of beer on the wall.\n")
        elif bottle == 0:
            result = ("No more bottles of beer on the wall, no more bottles of beer.\n"+
                      "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
        elif bottle < 0:
            raise ValueError('Invalid bottle number.')
    return result
