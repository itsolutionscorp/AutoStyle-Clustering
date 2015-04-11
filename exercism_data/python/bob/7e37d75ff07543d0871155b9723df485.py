class Bob:
    def hey(self, text):
        text = text.strip()

        allcaps = False

        for ch in text:
            if ch.islower():
                allcaps = False
                break
            elif ch.isupper():
                allcaps = True

        if allcaps:
            return 'Woah, chill out!'
        elif text == '':
            return 'Fine. Be that way!'
        elif text[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
