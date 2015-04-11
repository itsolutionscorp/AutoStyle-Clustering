module Hamming

	def Hamming.compute(first, second)
		x = 0
		for pos in 0..first.length - 1
			if second.length == pos then
				return x
			end
			if first[pos].chr != second[pos].chr then
				x += 1
			end
		end
		return x
	end
end
