class Phone:

    def __init__(self, phone_number):
        replace_me = ['(',')',' ','-','.']
        self.number = phone_number
        for elm in replace_me:
            self.number = self.number.replace(elm,'')

        if len(self.number) > 11 or len(self.number) < 10:
            self.number = '0000000000'

        elif len(self.number) == 11:
            if self.number[0] == '1':
                self.number = self.number[1:]
            else:
                self.number = '0000000000'

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        return '('+self.area_code()+') '+self.number[3:6]+'-'+self.number[6:]
