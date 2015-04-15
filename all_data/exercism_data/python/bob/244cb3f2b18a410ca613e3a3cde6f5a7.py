class Bob():
    def hey(self, text):
        text = text.strip()
        
        if self.__is_silence(text):
            answer = 'Fine. Be that way.'
        elif self.__is_shout(text):
            answer = 'Woah, chill out!'
        elif self.__is_question(text):
            answer = 'Sure.'
        else:
            answer = 'Whatever.'

        return answer

    def __is_silence(self, text):
        return text == ''

    def __is_shout(self, text):
        return text.isupper()

    def __is_question(self, text):
        return text.endswith('?')
