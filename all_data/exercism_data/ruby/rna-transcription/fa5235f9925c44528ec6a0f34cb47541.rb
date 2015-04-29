class Complement

  DNA_MAPPING = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_MAPPING = DNA_MAPPING.invert

  def self.of_dna(nucleotides)
    map(nucleotides, DNA_MAPPING)
  end

  def self.of_rna(nucleotides)
    map(nucleotides, RNA_MAPPING)
  end

  private

  def self.map(nucleotides, mapping)
    nucleotides.each_char.map { |c| mapping[c] }.join
  end

end
