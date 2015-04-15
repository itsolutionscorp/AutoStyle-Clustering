class Hamming
	class Strand
		def initialize(strand = [])
			@strand = strand.split("")
		end
		
		def length
			@strand.length
		end
		
		def take(number)
			@strand.take(number)
		end
		
		def distance_to(other)
			comparable_length = [self.length, other.length].min
			substrand_1 = self.take(comparable_length)
			substrand_2 = other.take(comparable_length)
			substrand_1.zip(substrand_2).map do |nucleotid_1, nucleotid_2|
				(nucleotid_1 <=> nucleotid_2).abs
			end.inject(0, &:+)
		end
	end
	
	def self.compute(first_strand, second_strand)
		return 0 if first_strand == second_strand
		Strand.new(first_strand).distance_to(Strand.new(second_strand))
	end
end
