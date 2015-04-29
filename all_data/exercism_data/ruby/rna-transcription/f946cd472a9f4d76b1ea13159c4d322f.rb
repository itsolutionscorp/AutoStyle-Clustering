class Complement
  def self.of_dna(dna_string)
    complements = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
    dna_string.chars.map {|x| complements[x]}.join
  end

  def self.of_rna(rna_string)
    complements = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }
    rna_string.chars.map {|x| complements[x]}.join
  end
end
