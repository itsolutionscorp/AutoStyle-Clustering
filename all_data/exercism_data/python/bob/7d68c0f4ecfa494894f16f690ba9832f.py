def hey(word):
    if word.strip() == '':
        return 'Fine. Be that way!'
    if word.isupper():
        return 'Whoa, chill out!'     
    if word[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
