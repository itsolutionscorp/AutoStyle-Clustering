class DNA
	@@DNA_BASES = 'ACGT'
	@@BASES = "#{@@DNA_BASES}U"

	def initialize(sequence)
		if !sequence.empty? and sequence !~ /\A[#{@@DNA_BASES}]+\Z/ then 
			raise ArgumentError, 'Invalid base in sequence'
		end
		@sequence = sequence
	end
	def nucleotide_counts()
		return @@DNA_BASES.chars.each_with_object({}) { |key, hash|
			hash[key] = count(key)
		}
	end
	def count(base)
		unless @@BASES.include?(base) then
			raise ArgumentError, 'Invalid base'
		end
		return @sequence.count(base)
	end
end
