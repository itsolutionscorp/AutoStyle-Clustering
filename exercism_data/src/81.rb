def compute(a, b)
		# Count each pair of characters that are not equal
		# except if one is nil (happens when a is longer than b)
		a.each_char.zip(b.each_char).count do |n1, n2|
			n2 != nil and n1 != n2
		end
	end