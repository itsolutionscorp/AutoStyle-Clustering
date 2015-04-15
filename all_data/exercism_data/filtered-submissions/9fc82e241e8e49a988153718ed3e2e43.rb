def compute(strandA, strandB)
		distance = strandA.split(//).zip(strandB.split(//)).count() do |nucleotideA, nucleotideB|
		  !nucleotideB.nil? && nucleotideA != nucleotideB
		end
	end