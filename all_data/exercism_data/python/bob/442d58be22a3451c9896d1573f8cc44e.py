def hey(statement):
    string = clean_statement = statement.strip()
    if not string: 
        return 'Fine. Be that way!'
    elif string.upper() == string and string.lower() != string:
        return 'Woah, chill out!'
    elif string[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
