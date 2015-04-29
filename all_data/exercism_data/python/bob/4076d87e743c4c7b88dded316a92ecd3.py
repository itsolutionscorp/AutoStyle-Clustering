class Bob():
    REPLIES = (
        ('yeld', "Woah, chill out!"),
        ('asked', "Sure."),
        ('empty', "Fine. Be that way."),
        ('', "Whatever.")
    )

    def statement_type(self, string):
        if string == "":
            return 'empty'
        elif string.upper() == string:
            return 'yeld'
        elif string[-1] == "?":
            return 'asked'
        return ''

    def hey(self, string):
        reply_type = self.statement_type(string)
        return dict(self.REPLIES)[reply_type]
