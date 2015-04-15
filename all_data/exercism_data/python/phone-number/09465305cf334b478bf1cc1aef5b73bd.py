import string
class Phone:
    number=''
    def __init__(self,what):
        for i in what:
           if i in string.digits:
               self.number+=i
        length = len(self.number)
        #just checking for correctness
        if length != 10:
            if length == 11:
                if self.number[0] == '1':
                    self.number = self.number[1:]
                else:
                    self.number = "".join(['0' for i in range(10)])
            else:
                self.number = "".join(['0' for i in range(10)])
        
    def pretty(self):
        new = []
        for cnt,i in enumerate(self.number):
            if cnt == 0:
                new.append('(')
            if cnt == 3:
                new.append(') ')
            if cnt == 6:
                new.append('-')
            new.append(i)
        return "".join(new)

    def area_code(self):
        return self.number[:3]
