class Series(object):

    def __init__(self, numbers) :
        self.numbers=[int(n) for n in numbers]

    def slices(self, n) :
        """Produces all series of length n from the sequence
        Raises ValueError if n<=0 or longer than the sequence
        """
        if n <= 0 or n > len(self.numbers) :
            raise ValueError(
            "Invalid slice length for this series: {0}".format(n) )
        
        return [list(self.numbers[x: x + n] ) 
                for x in range(0, len(self.numbers)-n + 1) ] 
