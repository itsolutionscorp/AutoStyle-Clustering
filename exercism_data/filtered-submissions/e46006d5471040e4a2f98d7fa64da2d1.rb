def compute(first_strand,second_strand)

		return false unless first_strand.is_a?(String) && second_strand.is_a?(String)
		return false unless first_strand.length > 0 && second_strand.length > 0

		if first_strand.length >= second_strand.length
			long_chars = first_strand.split('')
			short_chars = second_strand.split('')
		else
			long_chars = second_strand.split('')
			short_chars = first_strand.split('')
		end

		distance = 0

		short_chars.each_with_index do |c, i|
			distance += 1 if c != long_chars[i]
		end

		distance

	end