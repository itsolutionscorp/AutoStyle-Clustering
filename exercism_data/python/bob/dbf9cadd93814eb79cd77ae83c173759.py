def hey(s):
""" Returns Bob's response to input string s
"""
    if len(s) == 0 or s.isspace():          # s is empty or all white space
        return 'Fine. Be that way!'
    elif s.isupper():                       # s contains all uppercase letters
        return 'Whoa, chill out!'
    elif s[-1] == '?':                      # s ends with ?
        return 'Sure.'
    else:                                   # default case
        return 'Whatever.'
