class Phone:
    def __init__(self, text):
        self.number = ''.join(filter(str.isdigit, text))
        if len(self.number) == 11 and self.number[0] == '1':
            self.number = self.number[1:]
