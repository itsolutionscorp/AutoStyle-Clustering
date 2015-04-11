from __future__ import unicode_literals

def hey(prompt):
    cleaned_prompt = prompt.strip()
    message = 'Whatever.'

    if cleaned_prompt == '':
        message = 'Fine. Be that way!'
    
    elif cleaned_prompt.isupper():
        message = 'Whoa, chill out!'
    
    elif cleaned_prompt.endswith('?'):
        message = 'Sure.'
    
    return message
