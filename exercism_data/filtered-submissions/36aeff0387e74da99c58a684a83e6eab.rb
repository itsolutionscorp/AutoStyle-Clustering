def compute(str1, str2)
		i = 0
		char1 = str1.chars
		char2 = str2.chars
		
		char1.zip(char2).each do |c1, c2|
			if (c1 != c2) and !(c2.nil?)
				i += 1
			end
		end
		
		return i
		
		end