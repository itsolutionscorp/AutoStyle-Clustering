class Bob:

    def hey(self, sentence):          
        if sentence.strip() == '':
            return "Fine. Be that way!"
        elif sentence.strip().isupper():
            return "Woah, chill out!"
        elif sentence.strip().endswith('?'):
            return "Sure."
        else:
            return "Whatever."
