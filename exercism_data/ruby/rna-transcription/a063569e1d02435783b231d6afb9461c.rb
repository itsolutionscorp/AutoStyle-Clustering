class Complement

  DNA_TO_RNA = { 'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U' }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    dna.chars.map { |l| DNA_TO_RNA[l] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |l| RNA_TO_DNA[l] }.join
  end

end
