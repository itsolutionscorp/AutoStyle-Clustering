from string import punctuation

class Phone:
    def __init__(self, raw_number):

        self.raw_number = self.cleaned_number(raw_number)

        if self.is_invalid(self.raw_number):
            self.number = "0000000000"
        elif (len(self.raw_number) == 11 and
              self.raw_number.startswith("1")): 
            self.number = self.raw_number[1:]
        else:
            self.number = self.raw_number
    
    def cleaned_number(self, raw_number):
        return raw_number.translate(None, punctuation).translate(None, ' ')
    
    def is_invalid(self, number):
        if len(number) < 10:
            return True
        elif (len(number) == 11
              and number.startswith("2")):
            return True
            
    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "(%s) %s-%s" %(self.number[:3],
                              self.number[3:6],
                              self.number[6:]) 
