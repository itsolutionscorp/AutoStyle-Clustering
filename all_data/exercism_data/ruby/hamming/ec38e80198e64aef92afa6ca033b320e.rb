class Hamming

	def self.compute(strA, strB)
    strA.chars.zip(strB.chars).count { |a,b| (a != b && !b.nil?) }
	end

end
