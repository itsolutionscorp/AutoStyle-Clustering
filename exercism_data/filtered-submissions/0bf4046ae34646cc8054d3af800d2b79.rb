class Hamming
	def compute(seq1,seq2)
		length = [seq1,seq2].map(&:length).min - 1
		(0..length).count { |i| seq1[i] != seq2[i] }
	end
end
