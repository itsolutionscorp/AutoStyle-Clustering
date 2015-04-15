class Complement
  def self.of_dna(dna)
    dna.chars.map { |n| convert_dna n }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |n| convert_rna n }.join
  end

  def self.convert_rna(nucleotide)
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

  def self.convert_dna(nucleotide)
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
end
