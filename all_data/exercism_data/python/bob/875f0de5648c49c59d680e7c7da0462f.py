import string

#Takes string as input and returns a string response
def hey(input):
    #if the input string is empty
    if string.join(input.replace(' ','').split())=='':
        return 'Fine. Be that way!'
    #if the string is "loud" - all uppercase
    elif input.isupper():
        return 'Woah, chill out!'
    #if the string is a question
    elif input[-1]=='?':
        return 'Sure.'
    #if the string is not empty, not uppercase, and not a question  
    #or if input is not a string
    else:
        return 'Whatever.'






    
