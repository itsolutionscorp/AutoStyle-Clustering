class Complement

  def self.of_dna strand
    strand.chars.map{ |nuc| self.dna_to_rna(nuc) }.join
  end

  def self.of_rna strand
    strand.chars.map{ |nuc| self.rna_to_dna(nuc) }.join
  end

  def self.dna_to_rna nucleotide
    case nucleotide
    when "C"
      "G"
    when "G"
      "C"
    when "T"
      "A"
    when "A"
      "U"
    end
  end

  def self.rna_to_dna nucleotide
    case nucleotide
    when "C"
      "G"
    when "G"
      "C"
    when "U"
      "A"
    when "A"
      "T"
    end
  end

end
