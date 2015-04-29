class Hamming

	def self.compute(firstStrand, secondStrand)
		return 0 if firstStrand.eql? secondStrand
		if firstStrand.length < secondStrand.length
			return compare_and_count(firstStrand, secondStrand)
		else
			return compare_and_count(secondStrand, firstStrand)
		end
	end

def self.compare_and_count(shortStrand, longStrand)
	differences = 0
	0.upto(shortStrand.length-1) do |charPos|
		if !(shortStrand[charPos].eql? longStrand[charPos])
					differences += 1
				end
	end
	return differences
end

end
