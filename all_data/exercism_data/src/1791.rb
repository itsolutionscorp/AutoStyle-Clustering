def compute (a, b)
		count = 0
		zipped = a.chars.zip(b.chars)
		zipped.each do |a, b|
			if a && b && a != b
				count += 1
			end
		end
		count
	end
	
end