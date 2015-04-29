class Hamming
	def self.compute(strand_a,strand_b)
		number_of_differences = 0;
		length_of_short_strand  = [strand_a.length,strand_b.length].min 
		for i in (0...length_of_short_strand)
			number_of_differences += 1 if (strand_a[i] != strand_b[i])
		end
		number_of_differences;
	end
end
