def compute(first, second)
		#Make the strings the same length
		if first.length > second.length then
			first = first.slice(0..(second.length - 1))
		else
			second = second.slice(0..(first.length - 1))
		end

		#Check each character in the strings and count mismatches
		hamming_count = 0
		(0..(first.length - 1)).each do |i|
			if first.slice(i) != second.slice(i) then
				hamming_count = hamming_count + 1
			end
		end

		return hamming_count
	end