"""
Largely lifted/learned/studied from markwingerd's answer, because
    it's black boxy enough for me to dive in and understand more.
"""

alpha_conversion = {chr(c):chr(219-c) for c in range(ord('a'), ord('z')+1)}
numeric_conversion = {chr(c):chr(c) for c in range(ord('0'), ord('9')+1)}
conversion = dict(alpha_conversion, **numeric_conversion)

def encode(string):
    alphanum = [c for c in string.lower() if c.isalnum()]
    converted = convert(alphanum)
    return ' '.join([converted[i:i+5] for i in range(0, len(converted), 5)])

def decode(string):
    return convert(string.replace(' ', ''))

def convert(string):
    return ''.join([conversion[c] for c in string])
