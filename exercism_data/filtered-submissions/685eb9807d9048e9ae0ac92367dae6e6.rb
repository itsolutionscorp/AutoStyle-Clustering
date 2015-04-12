def compute(first, second)
		differences = 0
		length = [first.length, second.length].min - 1
		(0..length).each do |index|
			if first[index] != second[index]
				differences += 1
			end
		end

		return differences
	end