class Complement
  @@rna_complement_of_dna = {
      'G' => 'C',
      'C' => 'G',
      'A' => 'U',
      'T' => 'A'
  }

  @@dna_complement_of_rna = @@rna_complement_of_dna.invert

  # Generate RNA complement of a DNA strand
  def self.of_dna(dna_string)
    (dna_string.each_char.map { |dna_base| @@rna_complement_of_dna[dna_base]}).join
  end

  # Generate DNA complement of an RNA strand
  def self.of_rna(rna_string)
    (rna_string.each_char.map { |rna_base| @@dna_complement_of_rna[rna_base]}).join
  end
end
