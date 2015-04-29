def verse(num):
    result = ""
    if num == 0:
        result += "No more bottles of beer on the wall, no more bottles of beer.\n"
        result += "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    elif num == 1:
        result += "1 bottle of beer on the wall, 1 bottle of beer.\n"
        result += "Take it down and pass it around, no more bottles of beer on the wall.\n"    
    elif num == 2:
        result += "%s bottles of beer on the wall, %s bottles of beer.\n" % (num, num)
        result += "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    else:
        result += "%s bottles of beer on the wall, %s bottles of beer.\n" % (num, num)
        result += "Take one down and pass it around, %s bottles of beer on the wall.\n" % (num - 1)
    return result
  
    
def song(start, end=0):
    result = ""
    for i in range(start, end-1, -1):
        result += verse(i)
        result += "\n"   
    return result
