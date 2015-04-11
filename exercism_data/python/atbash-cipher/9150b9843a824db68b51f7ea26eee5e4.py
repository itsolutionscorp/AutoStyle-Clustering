import re
def decode(s):
    alpha = 'zyxwvutsrqponmlkjihgfedcba'
    newStr = ''
    for x in s.replace(' ',''):
        if x not in ['?','!','>','<','.','*','&','#']:
            newStr += alpha[-alpha.index(x.lower())-1]
        else:
            newStr += x
    return newStr

def encode(s):
    alpha = 'abcdefghijklmnopqrstuvwxyz'
    newStr = ''
    s = re.sub('[.?!, ]','',s)
    for x in s.replace(' ',''):
        if x not in ['?','!',' ','>','<','.','*','&','#','1','2','3','4','5','6','7','8','9','0']:
            newStr += alpha[-alpha.index(x.lower())-1]
        else:
            newStr += x
    newStr = ' '.join([newStr[x:x+5] for x in range(0,len(newStr),5)])
    return newStr
