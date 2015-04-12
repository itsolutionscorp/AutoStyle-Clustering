def compute(str1, str2)
		str1.chars.zip(str2.chars).delete_if{|x| x[0] == x[1] || x[1].nil? || x[0].nil?}.size
	end