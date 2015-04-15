class SumOfMultiples:
        
        def __init__(self, *args):
                self.multnumbers = args;         
                
        def to(self, maxnumber):
                self.sum = 0;
                if len(self.multnumbers) == 0:
                        self.sum += self.add_to_sum((3, 5), maxnumber);
                        return self.sum;
                else :
                        self.sum += self.add_to_sum(self.multnumbers, maxnumber);
                        return self.sum;

        def add_to_sum(self, multnum, maxnum):
                self.temp_sum = 0;
                for i in range(min(multnum), maxnum):
                        for j in multnum:
                                if i%j == 0:
                                        self.temp_sum += i;
                                        break;

                return self.temp_sum;
