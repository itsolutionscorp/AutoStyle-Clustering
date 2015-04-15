class SumOfMultiples():

	def __init__(self, *multiples):
		self._multiples = multiples or [3,5]

	def to(self, to_num):
		mults = [False] * to_num
		for multiple in self._multiples:
			for i in range(multiple, to_num, multiple):
				mults[i] = True
		return sum(index if bl else 0 for index, bl in enumerate(mults))
