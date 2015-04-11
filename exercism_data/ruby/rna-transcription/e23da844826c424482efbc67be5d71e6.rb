class Complement

  @dna_to_rna = Hash.new { raise(ArgumentError) }
  @dna_to_rna.merge! 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'
  @rna_to_dna = Hash.new { raise(ArgumentError) }
  @rna_to_dna.merge! @dna_to_rna.invert

  def self.of_dna strand
    strand.chars.map { |nuc| @dna_to_rna[nuc] }.join
  end

  def self.of_rna strand
    strand.chars.map { |nuc| @rna_to_dna[nuc] }.join
  end

end
