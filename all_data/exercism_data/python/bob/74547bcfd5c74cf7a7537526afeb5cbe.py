# -*- coding: utf-8 -*-
def hey(input):
    if not input.strip():
        return "Fine. Be that way!"
    if input.isupper():
        return "Waoh, chill out!"
    if input.endswith("?"):
        return "Sure."
    return "Whatever."

if __name__ == '__main__':
    input = raw_input()
    print hey(input)
