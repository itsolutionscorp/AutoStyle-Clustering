# This works, but I didn't really implement anything. I just used the 
# preexisting implementation.
def accumulate_cheating(l, f):
    return list(map(f, l))

# OK, here's a real implementation.
def accumulate(l, f):
    o = []
    for i in l:
        o.append(f(i))
    return o
