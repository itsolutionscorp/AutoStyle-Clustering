class Hamming
	def self.compute(s, t)
		s_array = s.chars
		t_array = t.chars
		hamming_distance = 0
		i = 0

		loop do
			s_item = s_array[i]
			t_item = t_array[i]
			if s_array[i] != t_array[i] then
				 hamming_distance += 1 
			end

			i += 1
			break if i >= s_array.size or i >= t_array.size or i >= s_array.size
		end 
		return hamming_distance
	end 
end
