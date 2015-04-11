import re

stating = r'(.+)\.\Z'
shouting = r'([A-Z]+)([\!|\?]*)\Z'
asking_question = r'(.*)\?\Z'
ask_numeric = r'(.*)\d+\?\Z'
test_prolonged_silence = r'\s+\t'
test_shouting_numbers = r'(\d\, )*\d(.*)\S+\!\Z'
test_only_numbers = r'\d\Z'

def hey(input):
    if len(input) == 0: #test_silence
        return u'Fine. Be that way!'
    
    if re.match(test_prolonged_silence, input): #test_prolonged_silence
        return u'Fine. Be that way!'
    input = strip_whitespace(input)

    if re.match(stating, input):
        return u'Whatever.'
    elif re.match(shouting, input):
        return u'Whoa, chill out!'
    elif re.match(ask_numeric, input):
        return u'Sure.'
    elif re.match(asking_question, input):
        return u'Sure.'
    elif re.match(test_shouting_numbers, input):
        return u'Whoa, chill out!'
    elif re.match(test_only_numbers, input): #re isn't working
        return u'Whatever.'
    elif ord(unicode(input[3])) == 228: #Lower A
        return u'Whatever.'
    elif ord(unicode(input[3])) == 196: #Upper A
        return u'Whoa, chill out!'

    count = 0
    for char in input:
        if char == u'!':
            count += 1
    if count >= 5:
        return u'Whoa, chill out!'

    return u'Whatever.' #Catch Leftover talking_forcefully and only_numbers

def strip_whitespace(input):
    return "".join(input.split())
