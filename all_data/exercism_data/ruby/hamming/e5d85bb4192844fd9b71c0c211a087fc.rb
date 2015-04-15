class Hamming
	def self.compute(strand1,strand2)
			first_is_longer(strand1,strand2) ? compare(strand2,strand1) : compare(strand1,strand2)
	end

	def self.compare(short_strand,long_strand)
		hamming_distance = 0
		short_strand.length.times do |i|
			hamming_distance+=1 if short_strand[i]!=long_strand[i]
		end
		hamming_distance
	end


	def self.first_is_longer(strand1,strand2)
		strand1.length >= strand2.length
	end
end
