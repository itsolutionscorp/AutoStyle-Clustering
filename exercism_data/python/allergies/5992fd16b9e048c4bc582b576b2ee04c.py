# Class to keep track of a person's allergies
class Allergies:

    # Constant mapping of allergens and their particular codes
    ALLERGY_MAPPING = {1:'eggs',2:'peanuts',4:'shellfish',8:'strawberries',
                       16:'tomatoes',32:'chocolate',64:'pollen',128:'cats'}

    # Initialize class given a particular allergy score
    def __init__(self, score):

        # Allergy score
        self.score = score

        # List of detected allergies
        self.list = []

        # Build the list of allergies
        self.calculate_allergies()

    # Build the list of allergies based on the supplied score
    def calculate_allergies(self):

        # Go through the list of allergy mappings, from lower to higher
        for allergy_code in sorted(self.ALLERGY_MAPPING.keys()):

            # Check if the score reports an allergy to this particular allergen
            if self.score & allergy_code:

                # Add the name of the allergen to the list of detected allergies
                self.list.append(self.ALLERGY_MAPPING[allergy_code])

    # Check if the person is allergic to a particular allergen
    def is_allergic_to(self, allergen):

        # Check if the allergen is present in the list
        return isinstance(allergen,basestring) and allergen in self.list
