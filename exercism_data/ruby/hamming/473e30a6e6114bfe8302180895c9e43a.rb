class Hamming
	def self.compute (first_string, second_string)
		if (first_string == second_string) then
			return 0
		end
		distance = 0

		length = [first_string.length, second_string.length].min

		for i in 0...length do
			if (first_string[i] != second_string[i]) then
				distance += 1
			end
		end

		return distance
	end
end
