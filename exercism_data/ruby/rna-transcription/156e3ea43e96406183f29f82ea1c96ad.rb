class Complement

  @dna_mapping = { 
    'G' => 'C', 
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  @rna_mapping = @dna_mapping.invert

	def self.of_dna(strand)
    strand.gsub(/./, @dna_mapping)
	end

  def self.of_rna(strand)
    strand.gsub(/./, @rna_mapping)
  end
end
