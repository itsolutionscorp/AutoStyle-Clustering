#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.isspace() or len(what) == 0:
        return 'Fine. Be that way!'
    #check for no alpha
    haschar_flag = False
    for c in what:
        if c.isalpha():
            haschar_flag = True
            break
    if not haschar_flag:
        if what[len(what)-1] == '?':
            return 'Sure.' #question without alpha
        return 'Whatever.'
    #end check for no alpha
    #check for yells
    yell_flag = True
    for c in what:
        if c.isalpha() and c.islower():
                yell_flag = False
    if yell_flag:
        return 'Whoa, chill out!'
    #end check for yells
    #check for questions
    if what[len(what)-1] == '?':
        return 'Sure.'
    #end check for questions
    return 'Whatever.'
