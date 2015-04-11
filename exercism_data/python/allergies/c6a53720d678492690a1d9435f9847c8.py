from operator import itemgetter

class Allergies:
    """Given personal allergy score prepare list of allergies"""

    allergy_scores = {
        'eggs': 1,
        'peanuts': 2,
        'shellfish': 4,
        'strawberries': 8,
        'tomatoes': 16,
        'chocolate': 32,
        'pollen': 64,
        'cats': 128,
    }

    def __init__(self, score):
        if score < 0:
            raise ValueError('Score has to be positive')
        self.score = score
        self.list = [x for x,y in sorted(self.allergy_scores.items(), key=itemgetter(1)) if self.score & y]     # Bitwise operation to select appropriate allergies

    def is_allergic_to(self, allergy):
        """Tests wheter a given allery is on one's list of allergies"""
        return allergy in self.list

# Standalone debugging
if __name__ == '__main__':
    a = Allergies(255)
    print a.is_allergic_to('eggs')
    print a.list
