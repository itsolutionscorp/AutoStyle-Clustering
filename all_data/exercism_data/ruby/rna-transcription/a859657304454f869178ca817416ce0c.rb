class RibonucleicAcid
	def initialize(dna)
		@dna = dna
	end

	def dna
		@dna
	end

	def to_s
		@dna
	end

	def inspect
		@dna
	end

	def ==(other)
		if other.class == RibonucleicAcid
    self.dna == other.dna
  else
  	self.dna == other
  end
  end
end

class String 
	def ==(other)
	  if other.class == RibonucleicAcid
	  	
	  	 self == other.dna
	  end
  end
end

class DeoxyribonucleicAcid
end
