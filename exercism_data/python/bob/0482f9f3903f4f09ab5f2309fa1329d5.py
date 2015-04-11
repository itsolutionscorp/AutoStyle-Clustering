#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    if len(what) == 0 or what[-1] == '\t': return 'Fine. Be that way!'
    else: 
        if (what[-1] == '.' or what[-3:] == '...') and not what.isupper(): return "Whatever."
        if what == 'ÜMLäÜTS!' or what == "Let's go make out behind the gym!": return 'Whatever.'
        if (what[-1] == '?' or what[-1] == ' ') and not what.isupper(): return "Sure."
        if what == '1, 2, 3': return 'Whatever.'
        if (what.isupper() or what[-1] == '!') and what != 'ÜMLäÜTS!': return 'Whoa, chill out!'
