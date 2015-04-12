class Hamming
	
	def compute (string_a, string_b)
		distance = 0
		[string_a.length, string_b.length].min.times do |i|
			distance += 1 if string_a[i] != string_b[i]
		end
		distance
	end
end

	
		
