class Allergies:

    allergens = ['cats','pollen','chocolate','tomatoes','strawberries','shellfish','peanuts','eggs']

    def __init__(self, score):
        self.list = self.generate_list(score)

    def is_allergic_to(self, allergy):
        if allergy in self.list:
            return True
        return False

    def generate_list(self, score):
        newList = []
        binary = "{0:b}".format(score)
        if len(binary) < 8:
            binary = "0"*(8-len(binary)) + binary
        while len(binary) > 8:
            binary = binary[1:]
        for i in range(8):
            if binary[i] == "1":
                newList.append(self.allergens[i])
        return newList[::-1]
