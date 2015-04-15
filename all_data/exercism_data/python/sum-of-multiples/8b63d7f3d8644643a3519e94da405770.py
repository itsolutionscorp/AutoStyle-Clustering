class SumOfMultiples:

    # Initialize with 3 and 5 unless other arguments are given
    def __init__(self, *args):
        if len(args) == 0:
            self.args = [3, 5]
        else:
            self.args = [arg for arg in args]
    
    def to(self, n):
        numbers = []
        # If divisible by one of the arguments, add the number to numbers
        for i in range(n):
            for arg in self.args:
                if i % arg == 0:
                    numbers.append(i)
                    break;
                
        return(sum(numbers))
    
