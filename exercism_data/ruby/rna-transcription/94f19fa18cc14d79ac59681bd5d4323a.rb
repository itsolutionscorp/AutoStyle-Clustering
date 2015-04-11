class Complement

  def self.of_dna(dna)
    rna=''
    dna.chars.each do |d|
      case d
      when 'G'
        c = 'C'
      when 'C'
        c = 'G'
      when 'T'
        c = 'A'
      when 'A'
        c = 'U'
      else
        c = d
      end
      rna += c
    end
    rna
  end
  
  def self.of_rna(rna)
    dna=''
    rna.chars.each do |r|
      case r
      when 'C'
        c = 'G'
      when 'G'
        c = 'C'
      when 'A'
        c = 'T'
      when 'U'
        c = 'A'
      else
        c = r
      end
      dna += c
    end
    dna
  end

end
