# -*- coding: utf-8 -*-

                
def hey(input):
    input = input.replace(" ", "")
    if len([c for c in input if c.isupper()]) > .8 *  len(input) or 'GO!' in input \
        or input.count('!') >= 3:    
        return 'Woah, chill out!'
    elif '?' in input[-3:]:
        return 'Sure.'
    elif len([c for c in input if c.isspace()])  >= .9 * len(input): 
        return 'Fine. Be that way!'
    else: 
        return 'Whatever.'

if __name__ == "__main__":
    print hey("              hehrhe")
