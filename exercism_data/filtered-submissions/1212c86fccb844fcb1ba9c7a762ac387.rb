class Hamming
	def compute(baseStrand, newStrand)
		hammingValue = 0
		maxLength = [baseStrand.length, newStrand.length].min - 1

		(0..maxLength).each do 
			|i| hammingValue+= 1 if baseStrand[i] != newStrand[i] end

		hammingValue
	end
end
