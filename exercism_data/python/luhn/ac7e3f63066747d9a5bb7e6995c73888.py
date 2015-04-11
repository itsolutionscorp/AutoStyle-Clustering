'''exer luhn'''

class Luhn:
    '''calculate and validate Luhn numbers'''

    def __init__(self, num):
        self.digits = [int(x) for x in str(num)]

    def addends(self):
        '''return the digits when every second from the right is doubled'''
        modified = self.digits
        modified.reverse()
        for ndx, itm in enumerate(modified):
            if ndx % 2 != 0:
                itm += itm
                if itm > 9:
                    itm -= 9
                modified[ndx] = itm
        modified.reverse()
        return modified

    def checksum(self):
        '''return the calculated checksum digit'''
        return sum(self.addends()) % 10

    def is_valid(self):
        '''verify a Luhn number'''

        return sum(self.addends()) % 10 == 0

    @staticmethod
    def create(partial_num):
        '''num is partial, calc checksum, append and return'''
        modified = [int(x) for x in str(partial_num * 10)]  # *10 to make room
        modified.reverse()                                  # same as addends
        for ndx, itm in enumerate(modified):
            if ndx % 2 != 0:
                itm += itm
                if itm > 9:
                    itm -= 9
                modified[ndx] = itm
        chksum = sum(modified) % 10                         # like .checksum
        if chksum:                                          # subtract from 10
            chksum = 10 - chksum
        return int(str(partial_num) + str(chksum))
