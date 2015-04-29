class Complement
  def self.of_dna(dna)
    rna = ""
    dna.chars.each do |x|
      case x
      when 'C'
        rna += 'G'
      when 'G'
        rna += 'C'
      when 'T'
        rna += 'A'
      when 'A'
        rna += 'U'
      end
    end
    return rna
  end

  def self.of_rna(dna)
    rna = ""
    dna.chars.each do |x|
      case x
      when 'G'
        rna += 'C'
      when 'C'
        rna += 'G'
      when 'A'
        rna += 'T'
      when 'U'
        rna += 'A'
      end
    end
    return rna
  end

end
