class Complement

  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'A' => 'U', 'T' => 'A'}
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    dna.split("").inject("") do |rna, nucleotide|
      rna += DNA_TO_RNA[nucleotide]
    end
  end

  def self.of_rna(rna)
    rna.split("").inject("") do |dna, nucleotide|
      dna += RNA_TO_DNA[nucleotide]
    end
  end
end
