def hey(message):
    if message.isupper():
        return 'Whoa, chill out!'
    if message.strip():
    	punctuation = message.strip()[-1]
    	return 'Sure.' if punctuation == '?' else 'Whatever.'
    return 'Fine. Be that way!'
