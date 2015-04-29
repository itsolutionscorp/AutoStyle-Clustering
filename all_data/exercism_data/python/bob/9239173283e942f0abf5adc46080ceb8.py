#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    alphas=caps=0
    if what.strip()=='':
        return 'Fine. Be that way!'
    for i in range(len(what)):
        if what[i].isalpha(): alphas+=1
        if what[i].isupper(): caps+=1
    if alphas==caps and alphas!=0: return 'Whoa, chill out!'
    if what.strip()[-1]=='?':
        return 'Sure.'
    else: return 'Whatever.'
