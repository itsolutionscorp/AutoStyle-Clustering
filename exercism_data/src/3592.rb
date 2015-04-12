module Hamming
	def compute(strand1, strand2)
		ham_dis = 0
		min_length = [strand1.length, strand2.length].min
		0.upto(min_length - 1) do |i|
			ham_dis +=1 unless strand1[i] == strand2[i]
		end
		ham_dis
	end
end
