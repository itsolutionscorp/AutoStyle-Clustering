class Allergies(object):
    def __init__(self, allerg_num):
        if allerg_num > 255:
            self.allerg_num = allerg_num - 256
        else:
            self.allerg_num = allerg_num

        self.list = self.list_create()

    def list_create(self):
        allerg_list = []
        while True:
            if self.allerg_num >= 128:
                allerg_list.append("cats")
                self.allerg_num -= 128

            elif self.allerg_num >= 64:
                allerg_list.append("pollen")
                self.allerg_num -= 64

            elif self.allerg_num >= 32:
                allerg_list.append("chocolate")
                self.allerg_num -= 32

            elif self.allerg_num >= 16:
                allerg_list.append("tomatoes")
                self.allerg_num -= 16

            elif self.allerg_num >= 8:
                allerg_list.append("strawberries")
                self.allerg_num -= 8

            elif self.allerg_num >= 4:
                allerg_list.append("shellfish")
                self.allerg_num -= 4

            elif self.allerg_num >= 2:
                allerg_list.append("peanuts")
                self.allerg_num -= 2

            elif self.allerg_num >= 1:
                allerg_list.append("eggs")
                self.allerg_num -= 1

            elif self.allerg_num == 0:
                break

        return list(reversed(allerg_list))

    def is_allergic_to(self, item):
        if item in self.list:
            return True
