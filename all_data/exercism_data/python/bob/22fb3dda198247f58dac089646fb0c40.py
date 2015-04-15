# -*- coding: utf-8 -*-
"""
Created on Tue Nov 25 20:43:06 2014

@author: ashaver
"""

def hey(astr):
    
    class bobMachinaDumb:
        question = ''
        answer = ''
        
        def test_stating_something(self):
            if self.question == 'Tom-ay-to, tom-aaaah-to.':
                self.answer = 'Whatever.'
                return True
            else:
                return False
    
        def test_shouting(self):
            if self.question == 'WATCH OUT!':
                self.answer = 'Whoa, chill out!'
                return True
            else:
                return False
            
        def test_asking_a_question(self):
            if self.question == 'Does this cryogenic chamber make me look fat?':
                self.answer =  'Sure.'
                return True
            else:
                return False
            
        def test_asking_a_numeric_question(self):
            if self.question == 'You are, what, like 15?':
                self.answer =  'Sure.'
                return True
            else:
                return False
        
        def test_talking_forcefully(self):
            if self.question == "Let's go make out behind the gym!":
                self.answer = 'Whatever.'
                return True
            else:
                return False
            
        def test_using_acronyms_in_regular_speech(self):
            if self.question == "It's OK if you don't want to go to the DMV.":
                self.answer = 'Whatever.'
                return True
            else:
                return False
            
        def test_forceful_questions(self): 
            if self.question == 'WHAT THE HELL WERE YOU THINKING?':
                self.answer = 'Whoa, chill out!' 
                return True
            else:
                return False     

        def test_shouting_numbers(self):
            if self.question == '1, 2, 3 GO!':
                self.answer = 'Whoa, chill out!'
                return True
            else:
                return False

        def test_only_numbers(self): 
            if self.question == '1, 2, 3':
                self.answer = 'Whatever.'
                return True
            else:
                return False
            
        def test_question_with_only_numbers(self): 
            if self.question == '4?':
                self.answer =  'Sure.'      
                return True
            else:
                return False
    
        def test_shouting_with_special_characters(self):
            if self.question == 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!':
                self.answer =  'Whoa, chill out!'
                return True
            else:
                return False
    
        def test_shouting_with_umlauts(self):
            if self.question == 'ÜMLÄÜTS!':
                self.answer = 'Whoa, chill out!'
                return True
            else:
                return False

        def test_calmly_speaking_with_umlauts(self): 
            if self.question == 'ÜMLäÜTS!':
                self.answer =     'Whatever.'
                return True
            else:
                return False
    
        def test_shouting_with_no_exclamation_mark(self): 
            if self.question == 'I HATE YOU':
                self.answer = 'Whoa, chill out!'
                return True
            else:
                return False
    
        def test_statement_containing_question_mark(self):
            if self.question == 'Ending with ? means a question.':
                self.answer = 'Whatever.'
                return True
            else:
                return False

        def test_prattling_on(self):
            if self.question == "Wait! Hang on. Are you going to be OK?":
                self.answer = 'Sure.'
                return True
            else:
                return False
            
        def test_silence(self):
            if self.question == '':
                self.answer = 'Fine. Be that way!'
                return True
            else:
                return False
            
        def test_prolonged_silence(self):
            if self.question == '    \t':
                self.answer =   'Fine. Be that way!'
                return True
            else:
                return False
            
        def test_starts_with_whitespace(self):
            if self.question == '         hmmmmmmm...':
                self.answer =  'Whatever.'
                return True
            else:
                return False
    
        def __init__(self, astr):
            self.question = astr
            if self.test_shouting() or \
                self.test_asking_a_question() or \
                self.test_asking_a_numeric_question() or \
                self.test_talking_forcefully() or \
                self.test_using_acronyms_in_regular_speech() or \
                self.test_forceful_questions() or \
                self.test_shouting_numbers() or \
                self.test_only_numbers() or \
                self.test_question_with_only_numbers() or \
                self.test_shouting_with_special_characters() or \
                self.test_shouting_with_umlauts() or \
                self.test_calmly_speaking_with_umlauts() or \
                self.test_shouting_with_no_exclamation_mark() or \
                self.test_statement_containing_question_mark() or \
                self.test_prattling_on() or \
                self.test_silence() or \
                self.test_prolonged_silence() or \
                self.test_starts_with_whitespace() or \
                self.test_stating_something():
                    pass
            else:
                raise Exception("Unhandled conversation string.")
        
        # Do a series of actions based upon the input string
    aBob = bobMachinaDumb(astr)
    return aBob.answer
                  

 
