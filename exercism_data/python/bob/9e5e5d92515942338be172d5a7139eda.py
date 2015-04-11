#!/usr/bin/python
import re

class Bob:
    """Class to instatiate simple chatbot"""

    def hey(e, sentence):
        if sentence.isupper():
            return "Woah, chill out!"
        elif sentence.endswith('?'):
            return "Sure."
        elif not sentence.strip():
            return "Fine. Be that way!"
        elif re.findall(sentence, u'[\xe4\xeb\xef\xf6\xfc]'):
            return "Whatever."
        else:
            return "Whatever."

if __name__ == '__main__':
    bob = Bob()
    print "Say something to Bob."
    while(1):
        sentence = raw_input()
        print bob.hey(sentence)
