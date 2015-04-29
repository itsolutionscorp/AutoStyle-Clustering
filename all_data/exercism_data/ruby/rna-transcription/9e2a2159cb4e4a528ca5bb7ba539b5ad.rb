class Complement

  def self.of_dna(dna)
    rna_complement = dna.chars.map do |char|
      if char == 'C'
        rna_complement << 'G'
      elsif char == 'G'
        rna_complement << 'C'
      elsif char == 'T'
        rna_complement << 'A'
      else
        rna_complement << 'U'
      end
    end

    return rna_complement.join('')
  end

  def self.of_rna(rna)

    dna_complement = self.of_dna(rna.gsub("U", "T"))
    
    return dna_complement.gsub("U", "T")
  end

end
