class Complement
  def self.of_dna(strand)
    rna = ""
    strand.split("").each do |s|
      case s
      when "C"
        rna += "G"
      when "G"
        rna += "C"
      when "T"
        rna += "A"
      when "A"
        rna += "U"
      end
    end
    rna
  end

  def self.of_rna(strand)
    dna = ""
    strand.split("").each do |s|
      case s
      when "G"
        dna += "C"
      when "C"
        dna += "G"
      when "A"
        dna += "T"
      when "U"
        dna += "A"
      end
    end
    dna
  end
end
