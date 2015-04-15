import sys
Strings = ['Sure.',     #input ends with '?'
           'Whoa, chill out!',  #input in ALL CAPS
           'Fine. Be that way!', #empty string
           'Whatever.', #anything else
           ]

def hey(input_string):
    if((not input_string) or str.isspace(input_string)):            #nic nebo mezery
        return(Strings[2])
    elif(str.isupper(input_string)):                                #input in ALL CAPS
        return(Strings[1])
    elif(input_string[-1] == '?'):                                  #question
        return(Strings[0])
    else:                                                           #anything else
        return(Strings[3])
     
