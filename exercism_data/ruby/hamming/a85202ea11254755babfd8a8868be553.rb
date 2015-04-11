class Hamming
	def self.compute(first, second)
		first_strand = DNA.new(first)
		second_strand = DNA.new(second)
		first_strand.distance(second_strand)
	end
end

class DNA
	attr_accessor :nucleaic_acids

	def initialize(strand)
		@nucleaic_acids = strand.chars.map { |char| NucleaicAcid.new(char) }
	end

	def nucleaic_acid(position)
		@nucleaic_acids[position]
	end

	def distance(other)
		strand_length = [self.length, other.length].min
		strand_length.times.count do |position|
			nucleaic_acid(position) != other.nucleaic_acid(position)
		end
	end

	def length
		@nucleaic_acids.length
	end
end

class NucleaicAcid
	attr_accessor :nucleaic_acid

	def initialize(char)
		@nucleaic_acid = char
	end

	def !=(other)
		@nucleaic_acid != other.nucleaic_acid	
	end
end
