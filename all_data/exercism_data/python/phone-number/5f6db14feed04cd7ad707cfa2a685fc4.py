
class  Phone(object):
    def __init__(self, number):
        self.number = self.clean(number)

    def clean(self, number):
        cleannumber = ''
        for c in number:
            if c.isdigit():
                cleannumber += c
        return cleannumber




