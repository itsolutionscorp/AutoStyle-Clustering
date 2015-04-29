class Bob:

    def hey(self, msg):
        self.inquiry = msg
        return self.resolve()

    def resolve(self):
        if not self.test_blank():
            response = 'Whatever.'
            if self.test_question():
                response = 'Sure.'

            if self.test_caps():
                response = 'Woah, chill out!'
        else:
            response = "Fine. Be that way."

        return response

    def test_blank(self):
        if not self.inquiry or \
                self.inquiry.isspace():
            return True
        else:
            return False

    def test_question(self):
        return self.inquiry.endswith('?')

    def test_caps(self):
        return self.inquiry.isupper()
