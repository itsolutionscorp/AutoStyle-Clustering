class Bob():
    def hey(self, sentence):
        # Answer 'Fine. Be that way!' if nothing is said
        if (sentence == None or sentence.strip() == ''):
            return 'Fine. Be that way!'

        # Answer 'Woah, chill out!' if someone yells (caps)
        elif str.isupper(sentence):
            return 'Woah, chill out!'

        # Answer 'Sure.' for all questions
        elif sentence.endswith("?"):
            return 'Sure.'

        # Any other cases? 'Whatever.'
        else:
            return 'Whatever.'
