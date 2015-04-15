class Complement

  def self.of_dna(dna)
    rna = ""
    dna.each_char do |i|
      if i == "C"
        rna << "G"
      elsif i == "G"
        rna << "C"
      elsif i == "T"
        rna << "A"
      elsif i == "A"
        rna << "U"
      end
    end
    rna
  end

  def self.of_rna(rna)
    dna = ""
    rna.each_char do |i|
      if i == "C"
        dna << "G"
      elsif i == "G"
        dna << "C"
      elsif i == "U"
        dna << "A"
      elsif i == "A"
        dna << "T"
      end
    end
    dna
  end
end
