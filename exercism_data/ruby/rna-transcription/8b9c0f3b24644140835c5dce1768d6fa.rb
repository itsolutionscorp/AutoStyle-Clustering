class Complement
  @dna_to_rna = {'G' => 'C',
             'C' => 'G',
             'T' => 'A',
             'A' => 'U'}

  @rna_to_dna = {'C' => 'G',
             'G' => 'C',
             'A' => 'T',
             'U' => 'A'}

  def self.of_dna(dna)
    rna_array = dna.chars.map { |e| @dna_to_rna[e] }
    rna_array.join
  end

  def self.of_rna(rna)
    rna_array = rna.chars.map { |e| @rna_to_dna[e] }
    rna_array.join
  end
end
