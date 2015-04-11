__author__ = 'janux'


class CharacterUtil:

    numbers = '0123456789'

    def __init__(self):
        pass

    @staticmethod
    def is_upper(ch):
        return (ch == ch.upper()) and (not CharacterUtil.is_number(ch))

    @staticmethod
    def is_number(ch):
        return ch in CharacterUtil.numbers

    @staticmethod
    def is_lower(ch):
        return not (CharacterUtil.is_number(ch) or CharacterUtil.is_upper(ch))


class Bob:

    def __init__(self):
        pass

    def hey(self, message):
        upper, lower, last, numbers = Bob.calculate(message.strip())
        if upper > 0 and lower <= 0:
            return 'Woah, chill out!'
        if last == '?':
            return 'Sure.'
        if upper + lower + numbers == 0:
            return 'Fine. Be that way!'
        return 'Whatever.'

    @staticmethod
    def calculate(message):
        filtered_message = [c for c in message if not c in '.,;:?-' and not c == ' ']
        upper = sum([1 for c in filtered_message if CharacterUtil.is_upper(c)])
        lower = sum([1 for c in filtered_message if CharacterUtil.is_lower(c)])
        numbers = sum([1 for c in filtered_message if CharacterUtil.is_number(c)])
        last = message[-1:]
        return upper, lower, last, numbers



