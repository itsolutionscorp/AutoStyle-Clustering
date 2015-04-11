class Bob:
    def __init__(self):
        pass

    def hey(self, say):
        newSay = ''
        if say:
            for i in range(len(say)):
                if say[i] != ' ':
                    newSay += say[i]
        if not newSay:
            return 'Fine. Be that way!'
        elif newSay == newSay.upper():
            return 'Woah, chill out!'
        elif newSay[len(newSay)-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
