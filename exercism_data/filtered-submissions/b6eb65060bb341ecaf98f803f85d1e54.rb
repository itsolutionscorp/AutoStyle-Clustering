def compute(arg1, arg2)
		count = 0
		arg1.chars.zip(arg2.chars).each do |a,b|
			if a != b
				count += 1
			end
		end
	return count
	end