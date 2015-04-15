module Complement

	def self.of_dna(dna)
		dna.gsub(DNA_PATTERN, DNA_TO_RNA_MAP)
	end

	def self.of_rna(rna)
		rna.gsub(RNA_PATTERN, RNA_TO_DNA_MAP)
	end

	private
	DNA_TO_RNA_MAP = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}
	DNA_PATTERN = Regexp.new(DNA_TO_RNA_MAP.keys.join('|'))

	RNA_TO_DNA_MAP = {
		'C' => 'G',
		'G' => 'C',
		'A' => 'T',
		'U' => 'A'
	}
	RNA_PATTERN = Regexp.new(RNA_TO_DNA_MAP.keys.join('|'))
end
