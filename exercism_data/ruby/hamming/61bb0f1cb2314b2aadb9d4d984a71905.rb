class Hamming
	def self.compute (s,t)
		counter = 0
		if s.length == t.length
			t.split('').each_with_index do |i,k|
				if i != s[k]
					counter += 1
				end
			end
		elsif s.length > t.length
			t.split('').each_with_index do |i,k|
				if i != s[k]
					counter += 1
				end
			end
		else
			s.split('').each_with_index do |i,k|
				if i != t[k]
					counter += 1
				end
			end
		end
		return counter
	end
end

p Hamming.compute("GAGCCTACTAACGGGAT", "CATCGTAATGACGGCCT")





# def hamming_distance (s, t)
# counter = 0
# s.split('').each_with_index do |i,k|
# 	if i != t[k]
# 		counter += 1
# 	end
# end
# return counter
# end


# p hamming_distance("GAGCCTACTAACGGGAT",
# "CATCGTAATGACGGCCT")
