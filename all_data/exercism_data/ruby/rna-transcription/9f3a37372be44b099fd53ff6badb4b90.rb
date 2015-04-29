class Complement

  COMPLEMENTS = { 
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(nucleotide)
    nucleotide.chars.map do |n| 
      COMPLEMENTS[n]
    end.join
  end

  def self.of_rna(nucleotide)
    nucleotide.chars.map do |n| 
      COMPLEMENTS.invert[n]
    end.join
  end

end
