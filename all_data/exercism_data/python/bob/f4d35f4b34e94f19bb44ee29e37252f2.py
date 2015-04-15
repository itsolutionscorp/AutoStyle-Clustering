def hey(input):
    if (not input or input.isspace()):
        return 'Fine. Be that way!'
    if (input.isupper()):
        return 'Whoa, chill out!'
    elif (input[-1] == '?'):
        return 'Sure.'
    else:
        return 'Whatever.'

if __name__ == '__main__':
    import sys
    input = sys.argv[1]
    print hey(input)
