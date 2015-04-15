class Complement
  @dna_complements = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  @rna_complements = @dna_complements.invert

  @dna_complements.default_proc = proc { raise ArgumentError, "Not an element of DNA" }
  @rna_complements.default_proc = proc { raise ArgumentError, "Not an element of RNA" }

  def self.of_dna(dna_string)
    dna_string.chars.map! {|c| @dna_complements[c] }.join
  end

  def self.of_rna(rna_string)
    rna_string.chars.map! {|c| @rna_complements[c] }.join
  end
end
