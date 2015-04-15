class Complement
  NUCLEOIDES_RNA_TO_DNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'} 
  NUCLEOIDES_DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A'}

  def self.of_dna(rna_nucleotides)
    transcript(rna_nucleotides, NUCLEOIDES_RNA_TO_DNA)
  end
  
  def self.of_rna(dna_nucleotides)
    transcript(dna_nucleotides, NUCLEOIDES_DNA_TO_RNA)
  end

private
  def self.transcript(strand, transcription_hash)
    strand.chars.map{|nucleotide| transcription_hash[nucleotide]}.join
  end  
end
