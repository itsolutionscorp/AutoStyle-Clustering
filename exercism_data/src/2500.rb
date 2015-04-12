class Hamming

	def compute(strand1, strand2)
		match_count = 0
		limit = [strand1.length, strand2.length].min
		strand1.chars.each_with_index do |letter, index|
			if index < limit
				match_count += letter != strand2.chars[index] ? 1 : 0
			end
		end
		match_count
	end

end
