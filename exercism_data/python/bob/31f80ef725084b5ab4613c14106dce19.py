import string

def hey(text):
    response = {'silence': 'Fine. Be that way!',
                'shouting': 'Whoa, chill out!',
		'question': 'Sure.',
		'default': 'Whatever.',
		}
    if text.strip() =='':
        return response['silence']
    if text.isupper():
        return response['shouting']
    if text.endswith('?'):
        return response['question']
    return response['default']
