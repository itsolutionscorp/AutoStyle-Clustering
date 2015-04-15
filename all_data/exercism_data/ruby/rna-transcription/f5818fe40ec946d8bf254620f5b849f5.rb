class Complement
  def self.of_dna(strand)
    rna_complement = []
    strand.each_char do |letter|
      case letter
      when "C"
        rna_complement << "G"
      when "G"
        rna_complement << "C"
      when "T"
        rna_complement << "A"
      when "A"
        rna_complement << "U"
      end
    end
    rna_complement.join("")
  end

  def self.of_rna(strand)
    dna_complement = []
    strand.each_char do |letter|
      case letter
      when "C"
        dna_complement << "G"
      when "G"
        dna_complement << "C"
      when "U"
        dna_complement << "A"
      when "A"
        dna_complement << "T"
      end
    end
    dna_complement.join("")
  end

end
