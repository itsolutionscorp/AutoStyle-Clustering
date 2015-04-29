dc = {
    0 : 'wink',
    1 : 'double blink',
    2 : 'close your eyes',
    3 : 'jump'
    }
def handshake(what):
    if isinstance(what,int):
        if what<=0:
            return []
        #getting rid of the 0b
        what = bin(what)[2:]
    else:
        #checking for irregularities in the input
        if (max([int(i) for i in what]))>1:
            return []
    ls = []
    for cnt,i in enumerate(reversed(what)):
        #if the input is five digits and the first elem is 1
        #it reverses the output
        if cnt==4 and i=='1':
            return ls[::-1]
        if i == '1':
            ls.append(dc[cnt])
    return ls
    
def code(what):
    #reversing the dict to find by value
    new ={value:key for key,value in dc.items()}
    if len(what)==1:
        return str(10**new[what[0]])
    outp = 0
    for i in what:
        if i not in new.keys():
            return '0'
        outp += 10**new[i]
    #checking if it's reversed in order to prepend the 1
    if new[what[0]]<new[what[1]]:
        return str(outp)
    else:
        return '1'+str(outp)
