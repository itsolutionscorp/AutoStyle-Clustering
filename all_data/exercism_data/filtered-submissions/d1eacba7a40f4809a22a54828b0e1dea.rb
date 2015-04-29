def compute(first, second)
		i = 0
		first.each_char do |a|
			if first[a] != second[a]
				i = i + 1
			end
		end
		i
	end