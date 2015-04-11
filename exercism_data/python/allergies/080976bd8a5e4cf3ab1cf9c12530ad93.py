# Dictionary of Allergy Scores
allergy_scores = [(1,'eggs'), (2,'peanuts'), (4,'shellfish'), (8,'strawberries'),
(16,'tomatoes'), (32,'chocolate'), (64, 'pollen'), (128,'cats')]

""" Allergy Class
"""
class Allergies(object):
    """ Initializes score and generates list of allergies according
        to score provided
    """
    def __init__(self, score):
        self.list = []
        self.score = score
        # Get first 8 bits of binary represenation of score
        score_b = "{0:08b}".format(score)[-8:]
        # Iterate over binary string in reverse and append
        # appropriate allergy for every 1 found
        for i in reversed(range(8)):
            if score_b[i] == '1':
                self.list.append(allergy_scores[7-i][1])

    """ Return True if allergic to input s
    """
    def is_allergic_to(self, s):
        return s in self.list

