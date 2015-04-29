# -*- coding: utf-8 -*-

                
def hey(input):
    
    if not input or input.isspace(): 
        return 'Fine. Be that way!'
    elif input.isupper(): 
        return 'Woah, chill out!'
    elif input[-1] == '?':
        return 'Sure.' 
    else: 
        return 'Whatever.'

if __name__ == "__main__":
    print hey("              hehrhe")
