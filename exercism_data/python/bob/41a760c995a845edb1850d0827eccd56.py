# File: bob.py
# Author: Theo Love
# Date: 2014-09-23
# Description: A solution to the Bob problem


    
# funciton that returns bob's respose to some input
def hey(input):
    # bob responds differently based on the input
    if input.isupper():                 # check first if bob is yelled at in all caps
        return "Whoa, chill out!"
    elif input.endswith('?'):           # 2nd if bob is asked a question 
        return "Sure."
    elif input.strip('\t\b ') == '':    # 3rd if bob gets response without text
        return "Fine. Be that way!"
    else:                               # all other cases
        return "Whatever."
