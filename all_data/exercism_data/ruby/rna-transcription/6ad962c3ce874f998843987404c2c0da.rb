class Complement
  def self.of_dna(dna_string)
    rna_string = ""
    complements = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
    dna_string.chars.map {|x| rna_string += complements[x]}
    return rna_string
  end

  def self.of_rna(rna_string)
    dna_string = ""
    complements = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }
    rna_string.chars.map {|x| dna_string += complements[x]}
    return dna_string
  end
end
