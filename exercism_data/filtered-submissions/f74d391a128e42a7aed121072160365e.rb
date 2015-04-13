def compute (first, second)
		if first.length <= second.length
			size = first.length
		else
			size = second.length
		end

		count = 0

		for i in 0..size-1
			if first[i] != second[i]
				count += 1
			end
		end

		return count
	end