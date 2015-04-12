class Hamming

	def compute(a,b)
		# compute the number of differences between a and b

		# analyze only character locations where both strings have characters, i.e. compare using the length of the shorter string
		shortest_length = [a.length, b.length].min

		(0...shortest_length).reduce(0) { |hamm,i| a[i] == b[i] ? hamm : hamm + 1 }

	end

end
