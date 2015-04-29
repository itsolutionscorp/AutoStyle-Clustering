class Complement
  DNA_TO_RNA = { 'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A' }  
  RNA_TO_DNA = DNA_TO_RNA.invert
  def self.of_dna(strand)
    strand.chars.map { |c| DNA_TO_RNA[c] }.join
  end
  def self.of_rna(strand)
    strand.chars.map { |c| RNA_TO_DNA[c] }.join
  end
end
