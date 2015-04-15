#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    res=''
    what = what.replace(" ","")
    what = what.replace('\t',"") 
    print what
    print len(what)
    if len(what) == 0 or what == " ":
        res= 'Fine. Be that way!'
    elif (what[-1] == '!' and what.isupper()==True) or (what.isupper()==True):
        res = 'Whoa, chill out!'
    elif what[-1]== '?':
        res = 'Sure.'
    else:
        res = 'Whatever.'            
        
    return res
