class Bob(object):
    def hey(self, str_):
        if str_ is not None:
            str_ = str_.strip()

        if not str_:
            return 'Fine. Be that way!'

        if str_ == str_.upper():
            return 'Woah, chill out!'

        if str_.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
