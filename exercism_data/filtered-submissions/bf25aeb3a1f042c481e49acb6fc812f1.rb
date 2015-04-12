class Hamming
	def compute(string1, string2)
		strand_length = [string1.length, string2.length].min
		hamming_count = 0
		(0...strand_length).each do |i|
			if string1[i] != string2[i]
				hamming_count += 1
			end
		end
		hamming_count
	end
end	
