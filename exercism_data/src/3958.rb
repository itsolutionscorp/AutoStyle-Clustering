class Hamming

	def compute(a, b)
		difference = 0
		(0..[a.length, b.length].min - 1).each do |i|
			if a[i] == b[i]
				difference
			else
				difference += 1
			end
		end
		difference
	end
end
