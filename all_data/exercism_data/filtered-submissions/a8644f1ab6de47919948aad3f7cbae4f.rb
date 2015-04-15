def compute(arg1, arg2)
		count = 0
		arg1.chars.zip(arg2.chars) do |a,b| count += a!= b ? 1 : 0 end
		return count
	end