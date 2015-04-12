def compute(a,b)
		a_iter = a.chars
		b_iter = b.chars

		count = 0
		loop do
			count += 1 if a_iter.next != b_iter.next
		end

		return count
	end