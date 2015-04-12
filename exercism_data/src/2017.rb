def compute(a, b)
		return 0 if a.nil? || b.nil?

		aToUse = a
		bToUse = b
		if a.length > b.length
			aToUse = b
			bToUse = a
		end

		diff = 0
		aToUse.chars.to_a.each_with_index do |f, i|
			if f != bToUse[i]
				diff = diff+1
			end
		end
		return diff
	end