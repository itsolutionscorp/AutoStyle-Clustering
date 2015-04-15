module Hamming
	def Hamming.compute(first, second)
		if first.length == second.length
			loops = first.length
		elsif first.length > second.length
			loops = second.length-1
		else first.length < second.length
			loops = first.length-1
		end

		first = first.split(//)
		second = second.split(//)

		counter = 0
		for letter in 0..loops
			if first[letter] != second[letter]
				counter+=1
			end
		end
		return counter
	end
end
