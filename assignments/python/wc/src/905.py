import re
def word_count(inp):
    ms = {}
    inp=inp.casefold() # everything "caseless"
    inp=re.sub("[^a-z0-9 ]","",inp)
    words = inp.split(" ")
    for i in words:
        if i == "":
            continue
        try:
            ms[i] = ms[i]+1
        except KeyError:
            ms[i] = 1
    return ms;
