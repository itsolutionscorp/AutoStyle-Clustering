class Complement

  def self.of_dna(dna_input)
    nucleotides={'G'=>'C','C'=>'G','T'=>'A','A'=>'U'}
    dna_input.chars.map { |e| nucleotides[e]  }.join
  end

  def self.of_rna(dna_input)
    nucleotides={'G'=>'C','C'=>'G','U'=>'A','A'=>'T'}
    dna_input.chars.map { |e| nucleotides[e]  }.join
  end

end
