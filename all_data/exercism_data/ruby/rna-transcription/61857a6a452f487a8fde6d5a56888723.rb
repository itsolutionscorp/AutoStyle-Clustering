class Complement
  def self.of_dna(dna_strand)
    d_nucleotides = dna_strand.split("")
    r_nucleotides = []

    d_nucleotides.each do |nucleotide|
      case nucleotide
      when "C"
        r_nucleotides << "G"
      when "G"
        r_nucleotides << "C"
      when "T"
        r_nucleotides << "A"
      when "A"
        r_nucleotides << "U"
      else
        nil
      end
    end

    r_nucleotides.join
  end

  def self.of_rna(rna_strand)
    r_nucleotides = rna_strand.split("")
    d_nucleotides = []

    r_nucleotides.each do |nucleotide|
      case nucleotide
      when "G"
        d_nucleotides << "C"
      when "C"
        d_nucleotides << "G"
      when "A"
        d_nucleotides << "T"
      when "U"
        d_nucleotides << "A"
      else
        nil
      end
    end

    d_nucleotides.join
  end
end
