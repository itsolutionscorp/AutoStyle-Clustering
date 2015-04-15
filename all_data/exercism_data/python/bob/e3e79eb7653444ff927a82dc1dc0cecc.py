#!/usr/bin/env python

def hey(statement):
    if len(statement.strip()) == 0:
        return 'Fine. Be that way!'
    elif statement.isupper():
        return 'Whoa, chill out!'
    elif statement.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'

def main():
    pass

if __name__ == '__main__':
    main()
