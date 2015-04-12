class Hamming
	def compute(strand_1, strand_2)
		hamming = 0	
		strand_length = [strand_1.length, strand_2.length].min - 1
		(0..strand_length).each do |strand|
			unless strand_1[strand] == strand_2[strand]
				hamming += 1
			end
		end
		hamming
	end
end
