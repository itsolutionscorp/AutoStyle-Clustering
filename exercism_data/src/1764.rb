class Hamming
	def compute(strandA, strandB)
		strandA.split(//).zip(strandB.split(//)).count do |a, b|
			if !a || !b
				false
			else
				a != b
			end
		end
	end
end
