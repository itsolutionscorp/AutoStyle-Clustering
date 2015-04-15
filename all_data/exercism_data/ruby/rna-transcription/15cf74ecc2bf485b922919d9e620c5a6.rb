class Complement

  def self.of_dna(dna)
    dna_to_rna = {
      'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U'
    }
    rna = ''
    dna.chars.each { |nucleotide| rna << dna_to_rna[nucleotide] }
    return rna
  end

  def self.of_rna(rna)
    #pretend the rna input is dna and get the complement rna
    dna = self.of_dna(rna.gsub("U", "T"))
    #switch the complement rna back to dna
    return dna.gsub("U", "T")
  end

end
