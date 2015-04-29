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
    input = s.strip()
    if len(input) == 0:
        return("empty")
    elif input.isupper():
        return("yelling")
    elif input.endswith("?"):
        return("question")
    else:
        return("other")
