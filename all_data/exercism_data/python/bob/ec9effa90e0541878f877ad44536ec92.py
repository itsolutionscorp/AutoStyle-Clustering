def hey(string):
    ''' Bob isn't very talkative. '''

    if not string.strip():
        return 'Fine. Be that way!'

    elif string.upper() == string:
        for character in string:
            if character.isalpha():
                return 'Whoa, chill out!'

    return 'Sure.' if string[-1] == '?' else 'Whatever.'
