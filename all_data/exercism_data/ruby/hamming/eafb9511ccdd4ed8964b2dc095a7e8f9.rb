class Hamming
	def self.compute(seq1,seq2)
		result = 0
		(seq1.chars).zip(seq2.chars) { |arr|  result += (arr[0] == arr[1] ? 0 : 1) }
		result
	end
end
