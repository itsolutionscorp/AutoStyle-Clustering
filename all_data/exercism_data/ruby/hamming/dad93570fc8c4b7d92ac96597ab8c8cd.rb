class Hamming
	class << self

		def compute(strand1, strand2)
			distance(strand1, strand2)
		end

		private

		def shortest_length(length1, length2)
			length1 < length2 ? length1 : length2
		end

		def distance(strand1, strand2)
			distance = 0
			length = shortest_length(strand1.length, strand2.length)
			for i in 0..length - 1
				if strand1[i] != strand2[i]
					distance = distance + 1
				end
			end
			distance
		end
	end
end
