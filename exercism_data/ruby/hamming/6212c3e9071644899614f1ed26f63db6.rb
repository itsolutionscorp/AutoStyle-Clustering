class String
	def nucleobase 
		self[0]
	end

	def tail
		self[1..self.size]
	end
end

class Hamming
	NUCLEOBASES = /^[ACGT]+$/

	def self.one_is_used_up(strands)
		strands[0].empty? or strands[1].empty?
	end

	def self.different_first_nucleobase(strands)
		strands[0].nucleobase != strands[1].nucleobase
	end

	def self.tails(strands)
		[strands[0].tail,strands[1].tail]
	end
	
	def self.calc(hamming, strands)
		return hamming if one_is_used_up strands 
		hamming += 1 if different_first_nucleobase strands
		calc hamming, tails(strands) 
	end

	def self.compute(strand1,strand2)
		if NUCLEOBASES.match(strand1+strand2).nil?
			raise "Unknown nucleobase(s) detected!"
		end
		calc hamming=0, [strand1, strand2]
	end

end
