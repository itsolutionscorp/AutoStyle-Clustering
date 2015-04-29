class String
	def map
		s = ''
		size.times {|i| s << yield(self[i]) }
		s
	end
end

class Complement
	DNA_COMPLEMENTS = {'C' => 'G', 'G' => 'C', 'A' => 'U', 'T' => 'A'}
	RNA_COMPLEMENTS = {'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T'}

	def self.of_dna strand
		strand.map { |bit| DNA_COMPLEMENTS[bit] }
	end

	def self.of_rna strand
		strand.map { |bit| RNA_COMPLEMENTS[bit] }
	end
end
