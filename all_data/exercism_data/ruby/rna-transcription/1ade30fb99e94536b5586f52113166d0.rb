module Complement
  def self.of_dna(dna_strand)
    transcibe(dna_strand, DNA_TO_RNA)
  end

  def self.of_rna(rna_strand)
    transcibe(rna_strand, RNA_TO_DNA)
  end


  DNA_TO_RNA = { 'G' => 'C','C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.transcibe(strand, transcription_hash)
    strand.chars.map { |n| transcription_hash[n] }.join
  end
end
