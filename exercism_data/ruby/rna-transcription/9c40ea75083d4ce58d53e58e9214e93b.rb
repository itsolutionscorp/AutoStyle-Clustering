class Complement
  def self.of_dna(rna)
    dna = ""
    rna.chars.each do |char|
      case char
      when 'C'
        dna << 'G'
      when 'G'
        dna << 'C'
      when 'T'
        dna << 'A'
      when 'A'
        dna << 'U'
      end
    end
    dna
  end

  def self.of_rna(dna)
    rna = ""
    dna.chars.each do |char|
      case char
      when 'C'
        rna << 'G'
      when 'G'
        rna << 'C'
      when 'U'
        rna << 'A'
      when 'A'
        rna << 'T'
      end
    end
    rna
  end
end
