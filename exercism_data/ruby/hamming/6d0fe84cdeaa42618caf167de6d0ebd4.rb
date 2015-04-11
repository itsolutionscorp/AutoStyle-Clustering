class Hamming
	def initialize() ; end
	def compute(str1, str2)
		strand_length = 0
		if str1.length <= str2.length
			strand_length = str1.length
		else
			strand_length = str2.length
		end
		if strand_length == 0
			puts "empty string"
		end
		input_strand1 = str1.chars.to_a
		input_strand2 = str2.chars.to_a
		hamming_count = 0
		ctr = 0

		while ctr < strand_length
			if input_strand1[ctr] != input_strand2[ctr]
				hamming_count += 1
			end
			ctr += 1
		end
		return hamming_count
	end
end	
