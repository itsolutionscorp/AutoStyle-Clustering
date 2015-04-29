import string

def word_count(input_string):
    ret = {}
    ret_keys = []
    for word in input_string.split():
        key = strip_punctuation(word.lower())
        if key == '': # gets rid of leftover colon keys
            pass
        elif key in ret_keys:
            ret[key] = ret[key]+1
        else:
            ret[key] = 1
            ret_keys = refresh_keys(ret)
    return ret

def refresh_keys(dict):
    ret = [k for k,v in dict.items()]
    return ret

def strip_punctuation(word):
    ret = word.translate(string.maketrans("",""), string.punctuation)
    return ret
