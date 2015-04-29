class Complement
  def self.of_dna(strand)
    strand.chars.map {|dna|
      dna_to_rna[dna]
    }.join
  end

  def self.of_rna(strand)
    rna_to_dna = dna_to_rna.invert

    strand.chars.map {|rna|
      rna_to_dna[rna]
    }.join
  end

  private

  def self.dna_to_rna
    { 'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U' }
  end
end
