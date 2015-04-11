class Allergies:
    algn = ["eggs","peanuts","shellfish","strawberries","tomatoes","chocolate","pollen","cats"]
    
    def __init__(self,total):
        self.total_score = total
        if self.total_score > 255:
            self.total_score -= 256
            
    def bool_table(self):
        b_t = [False]*len(self.algn)
        p = self.total_score
        i = 0
        while p != 0:
            if p % 2 != 0:
                b_t[i] = True
                p -= 1
            p //= 2
            i += 1
        return b_t
        
    def is_allergic_to(self,allergen):
        return self.bool_table()[self.algn.index(allergen)]
        
    @property
    def list(self):
        l = [self.algn[i] for i in range(len(self.algn)) if self.bool_table()[i]]
        return l
