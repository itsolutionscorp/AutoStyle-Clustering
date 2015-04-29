class Allergies(object):


    list = []

    allergy_value_dict = {
            "eggs":             1
            , "peanuts":        2
            , "shellfish":      4
            , "strawberries":   8
            , "tomatoes":       16
            , "chocolate":      32
            , "pollen":         64
            , "cats":           128
            }

    def __init__(self, allergy_score):
        self.allergy_score = allergy_score


    def is_allergic_to(self, food):
        if self.allergy_score & self.allergy_value_dict[food] == self.allergy_value_dict[food]:
            return True
        else:
            return False
