def verse(n):
    
    n = checkArgument(n)
    
    if n == 0:
        return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    
    elif n == 1:
        return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    
    else:
        return "{} bottles of beer on the wall, {} bottles of beer.\nTake one down and pass it around, {} bottle{} of beer on the wall.\n".format(str(n),
                                                                                                                                                  str(n),
                                                                                                                                                  str(n-1),
                                                                                                                                                  ("s" if n > 2 else ''),
                                                                                                                                                  )
    
def checkArgument(n):
    if type(n) == str:
        if n.isdigit(): return int(n)
        else: raise ValueError, "Function input must be an integer greater than zero."
    elif type(n) in [float,int]:
        if n >= 0: return int(n)
        else: raise ValueError, "Function input must be an integer greater than zero."
    
def song(n, d = 0):
    return '\n'.join([verse(i) for i in range(n,d-1,-1)]) + '\n'
