__author__ = 'jeffmarkey'

class Luhn:

    def __init__(self, number=''):

        if number != '':
            self.__number__ = number
            self.__checksum__ = int(str(self.__number__)[-1])
        pass

    def addends(self):
        number_string = str(self.__number__)
        return_list = []
        index = 0
        for character in number_string:
            if((len(number_string) - index) % 2 == 0):
                if(int(character)*2 > 9):
                    return_list.append((int(character) * 2) - 9)
                else:
                    return_list.append(int(character) * 2)
            else:
                return_list.append(int(character))
            index +=1
        return return_list

    def checksum(self):
        list_to_examine = self.addends()
        total = 0
        for line in list_to_examine:
            total = total + line
        return (total % 10)

    def is_valid(self):
        if(self.checksum() % 10 == 0):
            return True
        else:
            return False

    @staticmethod
    def create(content):
        number_string = str(content)+'0'
        to_anlayze = Luhn(int(number_string))
        checksum = (to_anlayze.checksum() % 10)
        return int(str(content)+str((10 - checksum) % 10))
