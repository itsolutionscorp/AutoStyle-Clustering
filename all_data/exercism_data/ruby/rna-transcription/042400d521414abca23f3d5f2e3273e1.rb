class Complement
  def self.of_dna dna
    dna.chars.map { |nucleotide| complement_dna_nucleotide(nucleotide) }.join
  end

  def self.of_rna rna
    rna.chars.map { |nucleotide| complement_rna_nucleotide(nucleotide) }.join
  end

  def self.complement_rna_nucleotide(rna)
    case rna
      when "C"
        "G"
      when "U"
        "A"
      when "A"
        "T"
      when "G"
        "C"
      else
        rna
    end
  end

  def self.complement_dna_nucleotide(dna)
    case dna
      when "C"
        "G"
      when "G"
        "C"
      when "T"
        "A"
      when "A"
        "U"
      else
        dna
    end
  end
end
