class DNA
	@@DNA_BASES = 'ACGT'
	@@BASES = "#{@@DNA_BASES}U"

	def initialize(sequence)
		raise ArgumentError, 'Invalid base in sequence' unless sequence.empty? or sequence.count("^#{@@DNA_BASES}") == 0
		@sequence = sequence
	end
	def nucleotide_counts()
		return @@DNA_BASES.chars.each_with_object({}) { |key, hash|
			hash[key] = count(key)
		}
	end
	def count(base)
		raise ArgumentError, 'Invalid base' unless @@BASES.include?(base)
		return @sequence.count(base)
	end
end
