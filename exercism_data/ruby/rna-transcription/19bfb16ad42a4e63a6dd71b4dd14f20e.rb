class Complement
  @complements = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna_string)
    error?( dna_string.chars.map! {|c| @complements[c] }.join )
  end

  def self.of_rna(rna_string)
    error?( rna_string.chars.map! {|c| @complements.invert[c] }.join )
  end

  def self.error?( string )
    raise ArgumentError, "ArgumentError" if string.empty?
    string
  end
end
