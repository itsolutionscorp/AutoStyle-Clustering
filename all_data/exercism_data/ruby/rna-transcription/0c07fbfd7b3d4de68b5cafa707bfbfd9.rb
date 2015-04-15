class Complement
  @complements = {'C' => 'G',
                 'G' => 'C',
                 'T' => 'A',
                 'A' => 'U'}

  @inverted_complements = @complements.invert

  def self.of_dna(dna_strand)
    complement(dna_strand, @complements)
  end

  def self.of_rna(rna_strand)
    complement(rna_strand, @inverted_complements)
  end

  def self.complement(rna_strand, mapping)
    rna_strand.chars.map { |x| mapping[x] }.join
  end
end
