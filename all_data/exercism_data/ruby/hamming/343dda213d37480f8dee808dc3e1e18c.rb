class	Hamming
	def self.compute(strand1, strand2)
    zipped_strands = strand1.split(//).zip(strand2.split(//))
    zipped_strands.flatten

    count(zipped_strands)
	end

	def self.count(zipped_strands)
		zipped_strands.count do |x, y|
		  y == nil || x == nil ? nil : x != y
		end	  
	end
end	
