class Hamming

	def compute(first,second)
		first = first.scan(/./)
		second = second.scan(/./)

		hamming = 0
		first.length.times do |i|

			break if second[i] == nil
			hamming += 1 if first[i] != second[i]
		end

		return hamming

	end

end
