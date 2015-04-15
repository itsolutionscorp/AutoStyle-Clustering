class Phone:
    def __init__(self, text):
        self.number = ''.join(filter(str.isdigit, text))
