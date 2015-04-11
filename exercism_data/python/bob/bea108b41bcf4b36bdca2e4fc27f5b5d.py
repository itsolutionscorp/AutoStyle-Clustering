import re

EC = ur'A-Z\u00C0-\u00D6\u00D8-\u00DC' #Extended Capitals
EL = ur'a-z\u00E0-\u00F6\u00F8-\u00FF' #Extended Lowercase

QUESTION_RESPONSE = 'Sure.'
SHOUT_RESPONSE = 'Whoa, chill out!'
SILENCE_RESPONSE = 'Fine. Be that way!'
NEUTRAL_RESPONSE = 'Whatever.'

#Any or no characters followed by a question mark
#Will match shouting questions
QUESTION_REGEX = re.compile(ur'^.*\?$', re.UNICODE)

#Any number or no non lower case characters, at least 1 uppercase character
#followed by any number or no non lower case characters
SHOUT_REGEX = re.compile(ur'^(?:[^'+EL+'])*(?:['+EC+'])+(?:[^'+EL+']*)$', re.UNICODE)

#Any number of whitespace characters or nothing
SILENCE_REGEX = re.compile(ur'^\s*$', re.UNICODE)

def hey(sentence):
    
    #Shout is first so that shouting questions will count
    #as shouting
    if re.match(SHOUT_REGEX, sentence):
        return SHOUT_RESPONSE
        
    elif re.match(QUESTION_REGEX, sentence):
        return QUESTION_RESPONSE
        
    elif re.match(SILENCE_REGEX, sentence):
        return SILENCE_RESPONSE
        
    else:
        return NEUTRAL_RESPONSE
    
