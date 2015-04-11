#! /usr/bin/env python

def hey(said):
    # Remove whitespace
    said = said.strip()
    # First, check for "empty" message
    if not said: return 'Fine. Be that way!'
    # NOW CHECK FOR SHOUTING
    # shouting is capital LETTERS, so double-check that.
    if said == said.upper() and said != said.lower():
        return 'Woah, chill out!'
    # Was Bob asked a question?
    if said[-1] == '?': return 'Sure.'
    # Default case.
    return 'Whatever.'

if __name__ == '__main__':
    from sys import argv
    print hey(argv[1] if len(argv) > 1 else '')
