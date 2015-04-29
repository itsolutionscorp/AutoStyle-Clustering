class Hamming(object):
    
    def __init__(self):
        pass
        
    def distance(self, strand1, strand2):
        
        list1 = list(strand1)
        list2 = list(strand2)
        count = 0
        
        for x, y in zip(list1, list2):
            if x == y:
                pass
            elif x != y:
                count += 1
            else:
                pass
                
        return count
        
hamming = Hamming()
