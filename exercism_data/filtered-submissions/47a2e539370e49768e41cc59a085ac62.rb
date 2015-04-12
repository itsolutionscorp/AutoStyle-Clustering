class Hamming
	def compute(strand_one, strand_two)
		hamming_distance_count = 0

		shortest_string_length = [strand_one.length, strand_two.length].min

		shortest_string_length.times do |strand_position|
			hamming_distance_count += 1 unless strand_one[strand_position] == strand_two[strand_position]
		end

		hamming_distance_count
	end
end
