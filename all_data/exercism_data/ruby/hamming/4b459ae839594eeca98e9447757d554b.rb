class Hamming
	def self.compute(strand1, strand2)
		shorter_length = [strand1.length, strand2.length].min
		mutations = 0

		shorter_length.times { |position| mutations += 1 if strand1[position] != strand2[position] }
		
		mutations
	end 
end 