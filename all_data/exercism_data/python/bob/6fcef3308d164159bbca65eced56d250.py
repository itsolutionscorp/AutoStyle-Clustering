#!/bin/python

class Bob():
    def hey(self, question):
        
        if question.isupper():
            return 'Woah, chill out!'
        elif (question.endswith('?')) == True:
            return 'Sure.'
        elif question.isspace() or not question:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
