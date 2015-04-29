class Hamming
	def self.compute(strand1, strand2)
		runs = strand1.length <= strand2.length ? strand1.length : strand2.length
		count = 0
		runs.times do |position|
			count += 1 if strand1[position] != strand2[position]
		end
		count
	end 
end 
