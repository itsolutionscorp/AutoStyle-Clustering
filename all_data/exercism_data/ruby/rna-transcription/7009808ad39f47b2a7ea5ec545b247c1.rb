class Complement
  def self.of_dna strand
    strand.chars.map { |nucleotide| rna_complement[nucleotide]  }.join 
  end

  def self.of_rna strand
    strand.chars.map { |nucleotide| dna_complement[nucleotide]  }.join 
  end

  private

  def self.rna_complement
  	{
  		'G' => 'C',
  		'C' => 'G',
  		'T' => 'A',
  		'A' => 'U'
  	}
  end

  def self.dna_complement
    {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }
  end
end
