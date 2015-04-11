# Bob the teenager
# Behavior based on what will pass the particular cases in the test suite, since problem statement leaves many things undefined.
def hey(s):
    if s.strip() == '':
        return 'Fine. Be that way!'
    elif s.upper() == s and s.lower() != s:
        return 'Whoa, chill out!'
    elif s[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
