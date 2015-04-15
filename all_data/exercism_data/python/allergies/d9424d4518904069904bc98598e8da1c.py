from numpy import binary_repr

class Allergies:

    def __init__(self, num, list=[]):
        self.list = list
        self.allergies = [["eggs"], ["peanuts"], ["shellfish"], ["strawberries"],
                    ["tomatoes"], ["chocolate"], ["pollen"], ["cats"]]
        self.convert(num)

    def convert(self, num):
        if num > 255:
            num = 1
        for ind, num in enumerate(list(binary_repr(num, 8))[::-1]):
            self.allergies[ind].append(int(num))
        self.list = [x[0] for x in self.allergies if x[1] > 0]

    def is_allergic_to(self, allergy):
        for thing in self.list:
            if thing == allergy:
                return True
        return False
