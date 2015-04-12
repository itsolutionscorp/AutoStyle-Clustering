def compute(thing1, thing2)
		count = 0
		index = 0
		thing1.each_char do |char|
			count += 1 unless char == thing2[index]
			index += 1
		end
		count
	end