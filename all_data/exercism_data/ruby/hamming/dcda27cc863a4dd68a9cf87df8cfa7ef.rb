module Hamming
	def self.compute(strand_a, strand_b)
		short, long = [strand_a, strand_b].sort do |a, b|
			a.length <=> b.length
		end
		
		short.chars.each_with_index.count do |char, idx| 
			char != long.chars[idx]
		end
	end
end
