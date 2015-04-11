
class Phone:
    def __init__(self, string):
        string = ''.join(filter(str.isnumeric, string))
        if len(string) == 10:
            self.number = string
        elif len(string) == 11 and string.startswith('1'):
            self.number = string[1:]
        else:
            self.number = '0'*10
            
    def area_code(self):
        return self.number[:3]
    
    def pretty(self):
        return "({}) {}-{}".format(self.number[:3], self.number[3:6], self.number[6:])
