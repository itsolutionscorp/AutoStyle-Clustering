from __future__ import unicode_literals

def hey(prompt):
    cleaned_prompt = prompt.strip()

    if cleaned_prompt == '':
        return 'Fine. Be that way!'
    
    if cleaned_prompt.isupper():
        return 'Whoa, chill out!'
    
    if cleaned_prompt.endswith('?'):
        return 'Sure.'
    
    return 'Whatever.'
