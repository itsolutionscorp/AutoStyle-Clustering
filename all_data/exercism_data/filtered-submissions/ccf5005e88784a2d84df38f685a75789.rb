def compute(a,b)
		if a.length < b.length
			aa = a
			a = b
			b = a
		end

		missCount = 0
		pos = 0

		a.each_char{|i|
			if i != b[pos]
				missCount += 1
			end
			pos += 1
		}
		missCount
	end