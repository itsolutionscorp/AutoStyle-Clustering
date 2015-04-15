class Bob():
    def hey(self, text):
        text   = text.strip()
        answer = ''
        
        if text == '':                      # Silence
            answer = 'Fine. Be that way.'
        elif text.isupper():                # Shout
            answer = 'Woah, chill out!'
        elif text.endswith('?'):            # Question
            answer = 'Sure.'
        else:                               # Statement (or invalid input)
            answer = 'Whatever.'

        return answer
