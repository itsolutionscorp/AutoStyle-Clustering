# -*- coding: utf-8 -*-
from __future__ import unicode_literals

def hey(text):
    
    #yell: test contains all caps
    if text.isupper():
        return 'Whoa, chill out!'
        
    #Parse through characters in text to remove end whitespace and allow for question mark check  
    txt_list = list(text)
    txt_list2 = []
    for i in txt_list:
        if i != ' ':
            txt_list2.append(i)
        else:
            pass
            
    #question: text contains question mark at end of text.      
    if txt_list2[len(txt_list2)-1:len(txt_list2)] == ['?']:
        return 'Sure.'
        
    #address_ignore: if text lacks characters
    elif text.isspace() or text == '':
        return 'Fine. Be that way!'
    
    #anything_else: else statement    
    else:
        return 'Whatever.'
