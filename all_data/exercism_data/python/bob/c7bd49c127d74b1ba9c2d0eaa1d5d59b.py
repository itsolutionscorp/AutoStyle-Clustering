def hey(mssg):
    mssg = mssg.strip() 
    if mssg.isupper():
        return 'Whoa, chill out!'
    if mssg.endswith('?'):
        return 'Sure.'
    if not mssg:
        return 'Fine. Be that way!'
    return 'Whatever.'
