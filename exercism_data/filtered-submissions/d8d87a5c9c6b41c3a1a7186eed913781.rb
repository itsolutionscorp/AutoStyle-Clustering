def compute(a,b)
		count = 0
		a.split(//).each_with_index do |sub, i|
			break if b.length == i
			count += 1 unless sub == b[i]
		end
		count
	end