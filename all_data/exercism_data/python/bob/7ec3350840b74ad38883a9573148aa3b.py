#Main File
def hey(str):
    
    #First case if asked a question and not being yelled at
    if str[len(str)-1:] == '?' and str.isupper() == False:
        return 'Sure.'
    
    #Next case if yelled at, assume yelling statement ends w/ '!'
    #or if string is in all caps indicating yelling
    elif str[len(str)-1:] is '!' or str.isupper():
        return 'Whoa, chill out!'
    
    #Next if he is addressed but nothing is said
    elif len(str) == 0 or '\t' in str:
        return 'Fine. Be that way!'
    
    #All else
    else:
        return 'Whatever.'
