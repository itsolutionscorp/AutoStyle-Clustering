import itertools

def sieve(no):
    
    oldlist=range(no)[2:]
    i = 0
    while i < len(oldlist):
        newlist = list(itertools.filterfalse(lambda x: not x%oldlist[i],oldlist))
        newlist.insert(i,oldlist[i]) #reinsert current number because the previous command deletes it
        i += 1
        oldlist = newlist

    return newlist
