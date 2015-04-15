class Complement

  def self.of_dna(dna)
    rna = ""
    dna.each_char do |c|
      case c
      when "G"
        rna << "C"
      when "C"
        rna << "G"
      when "T"
        rna << "A"
      when "A"
        rna << "U"
      end
    end
    return rna
  end

  def self.of_rna(rna)
    dna = ""
    rna.each_char do |c|
      case c
      when "C"
        dna << "G"
      when "G"
        dna << "C"
      when "A"
        dna << "T"
      when "U"
        dna << "A"
      end
    end
    return dna
  end

end
