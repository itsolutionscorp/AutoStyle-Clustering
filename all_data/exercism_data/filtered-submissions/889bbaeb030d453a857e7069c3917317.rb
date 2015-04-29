def compute(first_strand, second_strand)
		min_length = [first_strand.length, second_strand.length].min
		sum = 0

		min_length.times do |i|
			sum += 1 unless first_strand[i] == second_strand[i]
		end

		return sum
	end