class Complement
  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA = DNA_TO_RNA.invert
  def self.of_dna(nucleotides)
    nucleotides.each_char.map{|n| DNA_TO_RNA[n]}.join
  end
  def self.of_rna(nucleotides)
    nucleotides.each_char.map{|n| RNA_TO_DNA[n]}.join
  end
end
