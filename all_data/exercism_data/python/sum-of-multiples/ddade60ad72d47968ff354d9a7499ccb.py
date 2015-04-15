class SumOfMultiples():

    def __init__( self, *arg ):
        if len(arg) == 0:
            self.factors = [3,5]
        else:
            self.factors = list(arg)

    def to( self, N ):
        sumOfMultiples = 0
        for ii in range(2, N):
            allowed_number = False
            for f in self.factors:
                if ii % f == 0:
                    allowed_number = True
            if allowed_number:
                sumOfMultiples += ii
        return sumOfMultiples
