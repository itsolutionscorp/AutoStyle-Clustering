class Hamming
	def self.compute value_one,value_two
		diff_counter = 0
			
		min_length_value = find_min_length value_one, value_two

		(0..min_length_value.length-1).each do |i|
			if value_one[i]!=value_two[i]
				diff_counter+=1
			end
		end
		diff_counter
	end

	def self.find_min_length value_one, value_two
		value_one.length >= value_two.length ? value_two : value_one
	end
end
