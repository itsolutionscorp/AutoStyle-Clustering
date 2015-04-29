def compute(a,b)
		least = [a,b].min.size
		(least).times.count do |i|
			a[i] != b[i]
		end
	end