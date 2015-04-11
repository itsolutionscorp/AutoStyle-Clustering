import string

def hey(input):
    responses = {
            'question':'Sure.',
            'yelling':'Woah, chill out!',
            'empty':'Fine. Be that way!',
            'other':'Whatever.'
            }
    t = _input_type(input)
    return(responses[t])

def _input_type(s):
    input = _strip_whitespace(s)
    if len(input) == 0:
        return("empty")
    elif _is_allcaps(_strip_punctuation_and_numerals(input)):
        return("yelling")
    elif input[-1] == "?":
        return("question")
    else:
        return("other")

def _strip_whitespace(s):
    return(''.join(list(filter(lambda c: c not in string.whitespace, list(s)))))

def _strip_punctuation_and_numerals(s):
    return(''.join(list(filter(lambda c: c not in string.punctuation and c not in string.digits, list(s)))))

def _is_allcaps(s):
    r = True
    if len(s)>0:
        for c in s:
            if s.isupper():
                pass
            else:
                r = False
                break
        return(r)
    else:
        return False
