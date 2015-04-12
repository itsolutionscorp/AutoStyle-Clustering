class Hamming

	def compute s1, s2
		 
		return 0 if s1==s2
		return 1 if s1.length == 1 and s2.length == 1 and s1 != s2
		
		difference = 0

		if s1.length == s2.length 
			s1.chars.each_with_index do |c, i|
				difference = difference + 1 if c.to_s != s2[i]
			end
		end
		return difference
	end


	
end
