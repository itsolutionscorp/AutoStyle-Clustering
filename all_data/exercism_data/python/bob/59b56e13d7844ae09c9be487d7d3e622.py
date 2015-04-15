# -*- coding: utf-8 -*-
print "Bob does not like to talk..."
def hey(input):
    if not  input.strip():
        return "Fine. Be that way!"
    if input.isupper():
        return "Woah, chill out!"
    if input.endswith("?"):
        return "Sure."
    return "Whatever."

if __name__ == '__main__':
    print "Bob: 'Did you say something?"
    sinput = raw_input()
    input = sinput.decode('utf-8')
    print hey(input)
