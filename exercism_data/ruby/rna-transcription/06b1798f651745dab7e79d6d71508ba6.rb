class Complement

  @dna_to_rna = {
      'A' => 'U',
      'T' => 'A',
      'C' => 'G',
      'G' => 'C'
  }
  
  def self.of_dna(dna)

    rna = ""
    dna.each_char do |letter|
      rna.concat(@dna_to_rna[letter])
    end
    rna
    
  end

  def self.of_rna(rna)

    dna = ""
    rna.each_char do |letter|
      dna.concat(@dna_to_rna.invert[letter])
    end
    dna

  end

end
