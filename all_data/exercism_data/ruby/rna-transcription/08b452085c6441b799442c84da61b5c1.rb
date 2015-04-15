class Complement
  # Iteration 1
  def self.of_dna dna
    rna = String.new
    dna.split('').each do |nucleotide|
      rna += "U" if nucleotide == "A"
      rna += "G" if nucleotide == "C"
      rna += "C" if nucleotide == "G"
      rna += "A" if nucleotide == "T"
    end
    rna
  end

  def self.of_rna rna
    dna = String.new
    rna.split('').each do |nucleotide|
      dna += "T" if nucleotide == "A"
      dna += "G" if nucleotide == "C"
      dna += "C" if nucleotide == "G"
      dna += "A" if nucleotide == "U"
    end
    dna
  end
end
