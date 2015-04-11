def hey(what):
    length = len(what)

    if len(what) == 0:
        result = 'Fine. Be that way!'
    else:
        lastChar = what[length-1]
        nextLastChar = what[length-2]

        if what.isdigit():
            result = 'Whatever.'

        elif what.isupper():
            if nextLastChar.isdigit() and lastChar != '!':
                result = 'Sure.'
            else:
                result = 'Whoa, chill out!'

        elif lastChar == '\t':
            result = 'Fine. Be that way!'

        elif lastChar == '?':
            result = 'Sure.'

        else:
            result = 'Whatever.'

    return result
