class SumOfMultiples(object):
    def __init__(self, *multiples):
        if not multiples:
            self.multiples = [3, 5]
        else:
            self.multiples = []
            for multiple in multiples:
                self.multiples.append(multiple)
    def to(self, to_num):
        self.all_multiples = set()
        for multiple in self.multiples:
            self.all_multiples.update(_get_multiples(multiple, to_num))
        return sum(self.all_multiples)

def _get_multiples(multiple, to_num):
    return [i * multiple for i in range(to_num) if i * multiple < to_num]
