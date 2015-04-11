class Complement
  def self.of_dna(dna_strand)
    rna_strand = ""
    (0..(dna_strand.length-1)).each do |i|
      rna_strand << rna_complement(dna_strand[i])
    end
    rna_strand
  end

  def self.of_rna(rna_strand)
    dna_strand = ""
    (0..(rna_strand.length - 1)).each do |i|
      dna_strand << dna_complement(rna_strand[i])
    end
    dna_strand
  end

  private
    def self.rna_complement(nucleotide)
      case nucleotide
      when "A"
        "U"
      when "G"
        "C"
      when "C"
        "G"
      when "T"
        "A"
      else
        raise ArgumentError
      end      
    end

    def self.dna_complement(nucleotide)
      case nucleotide
      when "C"
        "G"
      when "G"
        "C"
      when "A"
        "T"
      when "U"
        "A"
      else
        raise ArgumentError
      end
    end
end
