class Complement

  @complements = { 
    'G' => 'C', 
    'C' => 'G',
    'T' => 'A',
    'U' => 'A'
  }

	def self.of_dna(dna_strand)
    @complements['A'] = 'U'
    of_strand(dna_strand)
	end

  def self.of_rna(rna_strand)
    @complements['A'] = 'T'
    of_strand(rna_strand)
  end

  private
  def self.of_strand(strand)
    strand.chars.map { |nucleotide| @complements[nucleotide] }.join('')
  end
end
