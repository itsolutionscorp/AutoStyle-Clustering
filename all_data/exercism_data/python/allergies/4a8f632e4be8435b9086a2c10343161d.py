class Allergies():

    def __init__(self, allergy_score):
        self.allegens = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()
        if allergy_score > 256:
                allergy_score -= 256
        self.allergy_score = bin(allergy_score)[2:].zfill(8) # removes first 2 letters "0b" from the string, then adds "0's" to 8 bit binary
        self.list = [item for item in self.allegens if self.is_allergic_to(item) ]

    def is_allergic_to(self, allegen):
        if self.allergy_score[7] == "1" and allegen == "eggs":
                return True
        elif self.allergy_score[6] == "1" and allegen == "peanuts":
                return True
        elif self.allergy_score[5] == "1" and allegen == "shellfish":
                return True
        elif self.allergy_score[4] == "1" and allegen == "strawberries":
                return True
        elif self.allergy_score[3] == "1" and allegen == "tomatoes":
                return True
        elif self.allergy_score[2] == "1" and allegen == "chocolate":
                return True
        elif self.allergy_score[1] == "1" and allegen == "pollen":
                return True
        elif self.allergy_score[0] == "1" and allegen == "cats":
                return True
        return False
