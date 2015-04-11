class Complement
  def self.of_dna(dna_strand)
    rna_output = ""
    dna_strand.each_char do |nucleotide|
      case nucleotide
      when "G"
        rna_output << "C"
      when "C"
        rna_output << "G"

      when "T"
        rna_output << "A"

      when "A"
        rna_output << "U"
      else
        raise "Invalid input!"
      end
    end
    rna_output
  end

  def self.of_rna(rna_strand)
    rna_output = ""
    rna_strand.each_char do |nucleotide|
      case nucleotide
      when "G"
        rna_output << "C"
      when "C"
        rna_output << "G"

      when "U"
        rna_output << "A"

      when "A"
        rna_output << "T"
      else
        raise "Invalid input!"
      end
    end
    rna_output
  end
end
