class Complement
  def self.of_dna(dna)
    nucleotides = dna.split("")
    complement = []
    nucleotides.each do |x|
      case x
      when "G"
        complement << "C"
      when "C"
        complement << "G"
      when "T"
        complement << "A"
      when "A"
        complement << "U"
      else
        return nucleotides
      end
    end
    complement.join
  end
  def self.of_rna(rna)
    nucleotides = rna.split("")
    complement = []
    nucleotides.each do |x|
      case x
      when "C"
        complement << "G"
      when "G"
        complement << "C"
      when "A"
        complement << "T"
      when "U"
        complement << "A"
      else
        return nucleotides
      end
    end
    complement.join
  end
end
