#sum_of_mutiples.py
#little boring no?
#sum of arithmetic series A(n)=(n/2)(A(1)+A(n))


class SumOfMultiples:

    def __init__(self, *args):
        self.bases = list(args)
        if len(self.bases) == 0:
            self.bases = [3, 5]

    def to(self, limit):
        #options:
        #1. Keep a list of all numbers to be summed.... the list could become rather large
        #2. Calculate sums of each base and subtract common multiples...it will be messy
        #3. Just track the sum but check each number to be added against any base lower than the current base
        # Option 3 sounds the best as the number of bases is constant so the sub-routine is O(n) and takes little space
        sum_of_multiples = 0
        self.bases = sorted(self.bases)
        if limit < self.bases[0]:
                return 0
        for current_base in range(0, len(self.bases)):
            base_multiplier = 1
            base_sum = 0
            while self.bases[current_base]*base_multiplier < limit:
                current_product = base_multiplier*self.bases[current_base]
                #do check and add to sum
                if all(current_product % self.bases[current_check] != 0 for current_check in range(0, current_base)):
                    base_sum += current_product
                base_multiplier += 1
            sum_of_multiples += base_sum

        return sum_of_multiples
