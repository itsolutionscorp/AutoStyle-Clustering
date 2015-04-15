class Complement

  @dna_mapping = { 
    'G' => 'C', 
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  @rna_mapping = @dna_mapping.invert

	def self.of_dna(strand)
    of_strand(strand, @dna_mapping)
	end

  def self.of_rna(strand)
    of_strand(strand, @rna_mapping)
  end

  private
  def self.of_strand(strand, mapping)
    strand.chars.map { |nucleotide| mapping[nucleotide] }.join('')
  end
end
