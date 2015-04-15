#
#   
#
def hey(what):
    # what is empty or just spaces ending with a tab
    if len(what) == 0 or (what.endswith('\t') and all(map(lambda c:c==' ', what[:-1]))):
        return 'Fine. Be that way!'
    
    def alpha(c):
        if isinstance(c, str):
            return str.isalpha(c)
        elif isinstance(c, unicode):
            return str.isalpha(c.encode('ascii', 'ignore'))
   
    # what is upper case and there is at least one letter
    if what == what.upper() and any(map(alpha, what)):
        return 'Whoa, chill out!'
   
    # what ends with a question mark
    if what and what[-1] in ' ?':
        return 'Sure.'
   
    # everything else
    return 'Whatever.'
