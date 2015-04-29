# Creates a dict. 219-c will get the invert of c
alpha_conversion = {chr(c):chr(219-c) for c in range(ord('a'), ord('z')+1)}
numeric_conversion = {chr(c):chr(c) for c in range(ord('0'), ord('9')+1)}
# Joins the dictionaries into one
conversion = dict(alpha_conversion, **numeric_conversion)

def encode(string):
    # Creates a list of only the Lowered alphanumeric characters.
    alphanum = [c for c in string.lower() if c.isalnum()]
    
    converted = convert(alphanum)

    # Takes the string and converts it to a list of 5 characters, then rejoins
    # it with a space and returns it.
    return ' '.join([converted[i:i+5] for i in range(0, len(converted), 5)])

def decode(string):
    no_spaces = string.replace(' ','')
    return convert(no_spaces)

def convert(string):
    return ''.join([conversion[c] for c in string])
