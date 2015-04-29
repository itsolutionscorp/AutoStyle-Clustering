class Complement

  @dna_rna_pairs = [
    [?A, ?U],
    [?T, ?A],
    [?C, ?G],
    [?G, ?C]
  ]

  @dna_to_rna_map = @dna_rna_pairs.to_h
  @rna_to_dna_map = @dna_rna_pairs.map {|a, b| [b, a]}.to_h

  def self.of_dna(dna)
    dna.chars.map {|c| @dna_to_rna_map[c]}.join
  end

  def self.of_rna(rna)
    rna.chars.map {|c| @rna_to_dna_map[c]}.join
  end

end
