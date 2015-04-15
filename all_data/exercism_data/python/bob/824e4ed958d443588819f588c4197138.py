import unicodedata

exclamatory_response = 'Whoa, chill out!'
interrogative_response = 'Sure.'
null_response = 'Fine. Be that way!'
default_response = 'Whatever.'

def hey(command):
    command = unicodedata.normalize('NFKD', command).encode('ascii','ignore')
    command = str(command).strip()
    if not command:
        return null_response
    elif any(c.isupper() for c in command) and command == command.upper():
        return exclamatory_response
    elif command[-1] == '?':
        return interrogative_response
    else:
        return default_response

if __name__ == '__main__':
    print hey("Let's go make out behind the gym!")
