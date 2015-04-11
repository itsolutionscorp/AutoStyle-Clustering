import sys
import re


responses = {}
responses['shouting'] = 'Whoa, chill out!'
responses['question'] = 'Sure.'
responses['indirect'] = 'Fine. Be that way!'
responses['default'] = 'Whatever.'

question_re = re.compile('\?$')
shouting_re = re.compile('[^a-z]+\!*')
has_upper_re = re.compile('[A-Z]')
leading_whitespace_re = re.compile('^\s+$')
    
def hey(input):
    case = 'default'
     
    if input.upper() == input and shouting_re.search(input) and has_upper_re.search(input):
        case = 'shouting'
    elif question_re.search(input):
        case = 'question'
    elif leading_whitespace_re.match(input) or not input:
        case = 'indirect' 

    response = responses[case]
    return response

if __name__ == '__main__':
    print hey(sys.argv[1])
