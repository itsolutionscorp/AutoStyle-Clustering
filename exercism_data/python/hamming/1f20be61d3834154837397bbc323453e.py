import operator

def distance(x,y):
    if len(x) != len (y):
        return False
    num = map(operator.eq, x, y).count(True)
    num2 = len(x)
    if num < num2:
        return num2 - num
    else:
        return num - num2
