class Phone(object):

    def __init__(self, s):
        
        s = ''.join([x for x in s if x.isdigit()])
        
        if len(s) == 11 and s[0] != '1':
            s = '0'*10
        elif len(s) == 11 and s[0] == '1':
            s = s[1:]
        elif len(s) != 10:
            s = '0'*10
            
        self.number = s

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        n = self.number
        return '(' + n[:3] + ') ' + n[3:6] + '-' + n[6:]
