class Hamming

	def self.compute( first_strand, second_strand )
		distance = 0

		return distance if first_strand == second_strand
		
		limit_by_length(first_strand, second_strand).each_with_index do |char, index|
			distance += 1 if char != second_strand[index]
		end

		distance
	end


	private

	def self.limit_by_length(toLimit, limiting_string)
			toLimit.chars.take(limiting_string.length)
	end	
end
