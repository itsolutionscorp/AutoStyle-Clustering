class Hamming
	def compute(string1, string2)
		
		if string1.length > string2.length
			string1 = string1 [0..(string2.length - 1)]
		end

		zipped =string1.split("").zip string2.split("")
		count = 0

		zipped.each do |symbol1, symbol2|
			if symbol1 != symbol2
				count += 1
			end
		end

		return count
	end
end
