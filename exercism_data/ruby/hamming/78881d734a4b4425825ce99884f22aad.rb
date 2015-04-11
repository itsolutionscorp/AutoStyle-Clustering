class Hamming

	def self.compute (first, second)

	if first == second
		return 0
	end

	index = 0
	tally = 0

	first = first.split('')
	second = second.split('')

	first.each do |char|
		if char != second[index]
			tally += 1
		end
		index += 1
	end

	return tally
	end

end
