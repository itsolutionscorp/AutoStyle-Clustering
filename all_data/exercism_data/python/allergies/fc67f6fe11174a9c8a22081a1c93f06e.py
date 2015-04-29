# the idea is to use binary
# scores = {
#     "eggs": 1, # 2^0
#     "peanuts":2, # 2^1
#     "shellfish": 4,
#     "strawberries": 8,
#     "tomatoes": 16,
#     "chocolate" 32,
#     "pollen": 64,
#     "cats": 128 
# }

scores = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats" ]

class Allergies:
    def __init__(self, score):	
        bin_score = bin(score)[:1:-1] # convert to binary, reverse order
        self._list = [ scores[idx] for idx,v in enumerate(bin_score) if v == '1' and idx < len(scores)]

    @property
    def list(self):
        return self._list
	
    def is_allergic_to(self, thing):
        return thing in self._list
