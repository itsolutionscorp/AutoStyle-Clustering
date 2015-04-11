# -*- coding: utf-8 -*-
print "Bob does not like to talk..."



def hey(input):
    
    if input == "":
        return "Fine. Be that way!"
    if input[-1] == "?":
        if input[:-1].isupper() == True:
            return "Woah, chill out!"
    if input.endswith("?") == True:
        return "Sure."
#    if input.endswith("!") == True:
#        return "Woah, chill out!"
    if input.isupper() == True:
        return "Woah, chill out!"
    if input.isspace() == True:
        return "Fine. Be that way!"
    return "Whatever."

if __name__ == '__main__':
#while True: 
    print "Bob: 'Did you say something?"
    sinput = raw_input()
    input = sinput.decode('utf-8')
    print hey(input)
