class Complement

  @hash = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna)
    dna.chars.inject("") {|str, elem| str << @hash[elem]}
  end

  def self.of_rna(dna)
    dna.chars.each.inject("") { |str, elem| str << @hash.invert[elem] }
  end

end
