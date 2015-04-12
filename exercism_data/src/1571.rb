class Hamming
	def compute(strand1,strand2)
		count = 0
		strand1.chars.zip(strand2.chars).each do |first, second|
			if first != second	 	
			count = count + 1
			end
		end
		count
	end
end
