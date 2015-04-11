class Hamming
	def self.compute(x, y)
		if x == y
			0
		else
			x_nucleotides = x.split("")
			y_nucleotides = y.split("")
			define_order(x_nucleotides, y_nucleotides)
			calculate_hamming_distance
		end
	end

	def self.define_order(x_nucleotides, y_nucleotides)
		if y_nucleotides.count > x_nucleotides.count
			@first_strand = x_nucleotides
			@second_strand = y_nucleotides
		else
			@first_strand = y_nucleotides
			@second_strand = x_nucleotides
		end
	end

	def self.calculate_hamming_distance
		hamming_distance = 0
		@first_strand.each_with_index do |n, i|
			if n != @second_strand[i]
				hamming_distance += 1
			end
		end
		hamming_distance
	end
end
