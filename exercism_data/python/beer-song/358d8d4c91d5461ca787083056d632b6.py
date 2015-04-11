numbeer = "{0} bottle of beer on the wall, {0} bottle of beer.\n"
takeitdown = "Take it down and pass it around, {0} bottle of beer on the wall.\n"
end = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"

def verse(n):
    if n == 0:
        return end
    if n == 1:
        return numbeer.format(n) + takeitdown.format('no more')
    return numbeer.format(n) + takeitdown.format(n-1)

def song(*args):
    if len(args) == 1:
        return '\n'.join([verse(args[0]-i) for i in xrange(args[0]+1)]) + '\n'
    else:
        return '\n'.join([verse(max(args)-i) for i in xrange(abs(args[0]-args[1]))]) + '\n'
