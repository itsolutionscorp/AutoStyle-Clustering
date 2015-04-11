punc = "!@#$%^&*()_+{[]}\|:;<,.>?"

def word_count(s):
    d = {}
    x = clean_punc(s)
    x = clean_space(x)
    phrase_list = x.split(' ')
    for wurd in phrase_list:
        if wurd.upper() in d.keys():
            d[wurd.upper()] += 1
        elif wurd.lower() in d.keys():
            d[wurd.lower()] += 1
        elif wurd in d.keys():
            d[wurd] += 1
        else:
            d[wurd] = 1
    return d

def clean_punc(s):
    a = s
    for x in punc:
        a = a.replace(x,'')
    return a

def clean_space(s):
    x = s
    while (x.find("  ") != -1):
        x = x.replace("  ", " ")
    return x
