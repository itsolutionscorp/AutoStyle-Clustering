class Hamming
	def compute(string1,string2)
		#make sure they are uppercase
		string1.upcase!
		string2.upcase!
		if string2.length < string1.length
			short_sequence = string2
			long_sequence = string1
		else
			short_sequence = string1
			long_sequence = string2
		end

		total = 0
		short_sequence.chars.each_with_index do |char,index|
			if char != long_sequence[index]
				total = total + 1
			end
		end
		total
	end
end
