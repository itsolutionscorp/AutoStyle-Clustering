class Hamming

	def self.compute(foo, bar)
		difference = 0

		foo.length.times do |c| 
			unless foo[c] == bar[c]
				difference += 1
			end
		end

		difference
	end
end
