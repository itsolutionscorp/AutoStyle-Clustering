class SumOfMultiples:
    def __init__(self, *args):
        if not args:
            self.nums = (3, 5)
        else:
            self.nums = args

    # @profile
    def to_old(self, num):
        ans = []
        for i in range(num):
            for j in self.nums:
                if i % j == 0:
                    ans.append(i)
        return sum(set(ans))

    # @profile
    def to(self, num):
        ans = []
        for j in self.nums:
            temp = [x for x in range(num) if x % j == 0]
            ans += temp
        return sum(set(ans))

if __name__ == "__main__":
    print(SumOfMultiples(3, 5, 7, 2).to(10**6))
    print(SumOfMultiples(43, 47).to_old(10**6))
