class Complement
  @code = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars.map{ |d| @code[d] }.join
  end
  
  def self.of_rna(rna)
    rna.chars.map{ |r| @code.invert[r] }.join
  end

end
