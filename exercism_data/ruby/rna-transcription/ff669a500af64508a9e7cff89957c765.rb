class Complement
  def self.of_dna strand
    strand.split('').map { |nucleotide| dna_complement[nucleotide]  }.join 
  end

  def self.of_rna strand
    strand.split('').map { |nucleotide| dna_complement.invert[nucleotide]  }.join 
  end

  private

  def self.dna_complement
  	{
  		'G' => 'C',
  		'C' => 'G',
  		'T' => 'A',
  		'A' => 'U'
  	}
  end
end
