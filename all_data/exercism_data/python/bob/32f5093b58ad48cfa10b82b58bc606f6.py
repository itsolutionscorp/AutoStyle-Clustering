import unicodedata
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #Remove all special letters 
    what = unicodedata.normalize('NFKD', what).encode('ascii','ignore')
    if(len(what)>1):
        if (what[-1]=='?'):
            #Check if yelled or if it just contains a digit
            if((what == what.upper()) and not(what[:-1].isdigit())):
                answer = 'Whoa, chill out!'    
            else:
                answer = 'Sure.'
        else:
            #Remove all special characters
            what = what.translate(None,'!@#$%^&*()_-+=:;"<,>.?/ \t')
            #Check again if being yelled at
            if((what == what.upper()) and not(what[:-1].isdigit()) and len(what)>1):
                answer = 'Whoa, chill out!'
            #If upon removing all special charecters the length is 0 then print Fine. Be that way!
            elif(len(what)<1):
                answer = 'Fine. Be that way!'
            else:
                answer = 'Whatever.'
    #If the length is 0 then print Fine. Be that way!
    else:
        answer = 'Fine. Be that way!'

    return answer
