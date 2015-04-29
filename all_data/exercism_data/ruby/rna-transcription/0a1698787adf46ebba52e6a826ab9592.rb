class Complement
  NUCLEOIDES_RNA_TO_DNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'} 
  NUCLEOIDES_DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A'}

  def self.of_dna(rna_nucleotides)
    rna_nucleotides.chars.map{|rna_nucleotide| NUCLEOIDES_RNA_TO_DNA[rna_nucleotide]}.join
  end
  
  def self.of_rna(dna_nucleotides)
    dna_nucleotides.chars.map{|dna_nucleotide| NUCLEOIDES_DNA_TO_RNA[dna_nucleotide]}.join
  end
end
