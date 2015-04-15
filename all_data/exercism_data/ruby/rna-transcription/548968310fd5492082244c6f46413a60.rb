# complement.rb
class Complement
  MAP = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  def self.of_dna(dna)
    dna.gsub(/./, MAP)
  end

  def self.of_rna(rna)
    rna.gsub(/./, MAP.invert)
  end
end
