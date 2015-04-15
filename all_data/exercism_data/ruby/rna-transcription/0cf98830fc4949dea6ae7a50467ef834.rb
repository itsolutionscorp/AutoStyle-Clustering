class Complement
  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(nucleotides)
    calculate(nucleotides, DNA_TO_RNA)
  end

  def self.of_rna(nucleotides)
    calculate(nucleotides, DNA_TO_RNA.invert)
  end

  private

  def self.calculate(nucleotides, mapping)
    nucleotides.split(//).collect { |n| mapping[n] }.join  
  end

end
