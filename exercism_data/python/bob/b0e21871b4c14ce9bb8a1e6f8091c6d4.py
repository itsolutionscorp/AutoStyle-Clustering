#Bob.py--The brains of Bob the magnificent


#hey function: Takes in a string for Bob to process and returns an appropriate response
#INPUT: string what
#OUPUT: string response
def hey(what):
    if isinstance(what, unicode):
        man = "".join(what.split())#strips the whitespace...man
        #precedence:is the man's words empty? Is the man yelling? Is the man asking? Whatever man.
        if not man:
            return 'Fine. Be that way!'

        elif man.isupper():
            return 'Whoa, chill out!'

        elif man[len(man)-1] == '?':
            return 'Sure.'

        else:
            return "Whatever."
    else:
        return "Whatever."
