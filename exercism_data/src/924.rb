def compute(strand_one, strand_two)


		shortest_string_length = [strand_one.length, strand_two.length].min


		@hamming_distance_count = 0
		@string_position = 0


			while @string_position < shortest_string_length

				if strand_one.chars[@string_position] != strand_two.chars[@string_position]
					@hamming_distance_count += 1
				end

				@string_position += 1

			end

		return @hamming_distance_count

	end