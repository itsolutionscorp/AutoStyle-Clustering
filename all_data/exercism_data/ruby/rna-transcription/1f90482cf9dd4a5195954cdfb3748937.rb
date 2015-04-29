class Complement
  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(nucleotides)
    nucleotides.split(//).collect { |n| DNA_TO_RNA[n] }.join
  end

  def self.of_rna(nucleotides)
    nucleotides.split(//).collect { |n| DNA_TO_RNA.invert[n] }.join
  end

end
