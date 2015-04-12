def compute(s, t)
		
		s = s.upcase
		t = t.upcase
		
		s_splitted = s.chars
		t_splitted = t.chars
		min_length = s.length
		
		if t.length < s.length
			min_length = t.length
		end
		
		count = 0
		
		min_length.times do |num|
			if !s_splitted[num].eql? t_splitted[num]
				count += 1
			end
		end
		
		return count
		
	end