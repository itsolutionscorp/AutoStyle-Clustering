class Complement
  DNA_TO_RNA_RULES = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA_RULES = DNA_TO_RNA_RULES.invert

  def self.of_dna(dna_strand)
    transcribe(dna_strand, DNA_TO_RNA_RULES)
  end
  
  def self.of_rna(rna_strand)
    transcribe(rna_strand, RNA_TO_DNA_RULES)
  end
  
  def self.transcribe(strand, transcription_rules)
    (strand.chars.map { |nucleotide| transcription_rules[nucleotide] }).join
  end
end
