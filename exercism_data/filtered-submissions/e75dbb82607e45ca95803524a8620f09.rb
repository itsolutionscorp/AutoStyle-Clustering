def compute(strand1, strand2)
		num = 0
		strand1.chars.to_a.each_with_index do |letter, idx|
			break if strand2.chars.to_a[idx].nil?
			if letter != strand2.chars.to_a[idx] then num += 1 end
		end
		return num
	end