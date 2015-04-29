class Matrix:
    def __init__(self, instr):
        self.rows = []
        self.columns = []
        lines = instr.split('\n')
        for line in lines:
            nums = []
            for num in line.split(' '):
                nums.append(int(num))
            self.rows.append(nums)

            for i in range(0, len(nums) - 1):
                if not len(self.columns) > i:
                    self.columns.append([])

                self.columns[i].append(nums[i])
