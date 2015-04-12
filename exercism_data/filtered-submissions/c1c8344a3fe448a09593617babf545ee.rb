class Hamming

	def compute(strand_one, strand_two)
		strand_one[0...strand_two.length].chars.each_with_index.count{|c,i| c != strand_two[i] }
	end
end
