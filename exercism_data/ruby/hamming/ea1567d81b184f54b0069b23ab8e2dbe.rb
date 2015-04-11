# Masonnl
# Ruby Exercism - Hamming

# Count the differences between two strands of strings
module Hamming

	# Compute the differences of two given strings (case-sensitive)
	def self.compute(first, second)
		first = first.chars
		second = second.chars

		distance = 0
		while !first.empty? && !second.empty?
			distance += 1 if first.shift != second.shift
		end
		distance
	end
end  # end Module Hamming
