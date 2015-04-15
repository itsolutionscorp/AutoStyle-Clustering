# -*- coding: utf-8 -*-
import re

def hey(txt):
    """
    function hey takes a string as input and returns a string response
    which is a function of the input string.
    """
    answers={'question':'Sure.',
            'screaming':'Woah, chill out!',
            'any_other':'Whatever.',
             'silence':'Fine. Be that way!'}
    #is it a question with at least one small cap
    if len(re.findall(u'[a-züöä0-9]+.*[?]$',txt))!=0:
        return answers['question']
    #there no small caps => either shouting or silence
    elif len(re.findall(u'[a-züöä]',txt))==0:
        # no uppercaps => silence or numbers only
        if len(re.findall(u'[A-ZÜÖÄ]',txt))==0:
            if len(re.findall('[0-9]',txt))==0:
                return answers['silence']
            else:
                return answers['any_other']
        #only upper caps => shouting
        else:
            return answers['screaming']
    else:
        return answers['any_other']
