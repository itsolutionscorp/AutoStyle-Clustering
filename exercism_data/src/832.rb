def compute(a,b)
		if a.length < b.length
			smaller = a 
			bigger  = b
		else
			smaller = b
			bigger  = a
		end
		count = 0
		smaller.split("").each_with_index do |s, i|
			if s != bigger.split("")[i]
				count += 1
			end
		end
		return count
	end