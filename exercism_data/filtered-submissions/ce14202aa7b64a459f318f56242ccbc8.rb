class Hamming

	def compute(string_one, string_two)
		count = 0
		([string_one.length, string_two.length].min).times do |i|
			if string_one[i] != string_two[i] 
				count += 1
			end
		end
		count
	end
	
end
