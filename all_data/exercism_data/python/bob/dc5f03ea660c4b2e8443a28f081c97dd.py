#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    if len(what) == 0 or what.endswith('\t'):
        return 'Fine. Be that way!'
    
    ret = 'Whatever.'
    
    if what.isupper():
        ret = 'Whoa, chill out!'
            
    if ret != 'Whoa, chill out!':
        if what.find('?'):
            for x in what:
                if x == '?':
                    ret = 'Sure.'
                elif x != ' ':
                    ret = 'Whatever.'
    
    return ret
