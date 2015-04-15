class Hamming

	def self.compute(first, second)
		sorted_string_array = [first, second].sort_by{ |s| s.length }
		sorted_string_array[0].split("").enum_for(:each_with_index).reject{ |char, i| char == sorted_string_array[1][i]}.length
	end

end
