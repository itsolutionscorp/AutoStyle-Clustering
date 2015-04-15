module Complement
  @mapping = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' } 

  def self.of_dna a
    a.chars.map { |c| @mapping[c] }.join
  end
  
  def self.of_rna a
    a.chars.map { |c| @mapping.invert[c] }.join
  end
end
