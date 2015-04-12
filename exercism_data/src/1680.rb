def compute(str1, str2)
		distance = 0
		sequence1 = str1.chars
		sequence2 = str2.chars
		
		sequence1.zip(sequence2).each do |c1, c2|
			distance += 1 unless (c1 == c2) or c2.nil?
		end
		
		return distance
	end