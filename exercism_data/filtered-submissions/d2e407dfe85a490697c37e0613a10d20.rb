def compute(first, second)

		score = 0

		first.size.times do |i|
			score += 1 if first[i] != second[i] && i < second.size
		end

		score

	end