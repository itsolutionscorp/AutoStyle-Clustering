#! /usr/bin/env python

def _is_question(said):
    return said.endswith('?')

def _is_shouting(said):
    return said.isupper()

def hey(said):
    said = said.strip()

    if not said:
        return 'Fine. Be that way!'
    # shouting trumps questions
    if _is_shouting(said):
        return 'Woah, chill out!'
    if _is_question(said):
        return 'Sure.'

    return 'Whatever.'

if __name__ == '__main__':
    from sys import argv
    print hey(argv[1] if len(argv) > 1 else '')
