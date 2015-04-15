class Complement
  def self.of_dna(dna)
    rna = ""
    for i in 0...dna.length
      case dna[i]
      when 'G'
        rna += 'C'
      when 'C'
        rna += 'G'
      when 'T'
        rna += 'A'
      when 'A'
        rna += 'U'
      else
        # uh oh
      end
    end
    return rna
  end

  def self.of_rna(rna)
    dna = ""
    for i in 0...rna.length
      case rna[i]
      when 'C'
        dna += 'G'
      when 'G'
        dna += 'C'
      when 'A'
        dna += 'T'
      when 'U'
        dna += 'A'
      else
        # uh oh
      end
    end
    return dna
  end
end
