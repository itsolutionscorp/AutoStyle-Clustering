class DNA

	def initialize(value)
		@main = value
	end

	def hamming_distance(value)
		distance = 0
		@main.chars.take(value.length).zip value.chars do |arr|
			distance += 1 if arr[0] != arr[1]
		end
		distance
	end
end
