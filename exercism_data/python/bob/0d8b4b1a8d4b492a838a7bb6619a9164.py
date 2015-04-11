from __future__ import unicode_literals
import string


def hey(speak):
                says = {1: 'Sure.', 2: 'Whoa, chill out!', 3: 'Fine. Be that way!', 4: 'Whatever.'}
                return says[breakdown(speak)]



def breakdown(speak):
                if  speak.isspace() or not speak: #silence
                                return 3
                elif speak.upper() == speak and  any(c.isalpha() for c in speak): #yell
                                return 2
                elif  speak[-1] == '?': #question
                                return 1
                else: #normal
                                return 4
