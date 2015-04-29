#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    if len(what) == 0 or what[len(what)-1] == '\t':
        return 'Fine. Be that way!'
    
    ret = 'Whatever.'
    
    lowerCase = set('abcdefghijklmnopqrstuvwxyzåäö')
    upperCase = set('ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ')

    if not any((c in lowerCase) for c in what):
        ret = 'Whoa, chill out!'
        if not any((x in upperCase) for x in what):
            ret = 'Whatever.'
            
    if ret != 'Whoa, chill out!':
        if what.find('?'):
            for x in what:
                if x == '?':
                    ret = 'Sure.'
                elif x != ' ':
                    ret = 'Whatever.'
    
    return ret
