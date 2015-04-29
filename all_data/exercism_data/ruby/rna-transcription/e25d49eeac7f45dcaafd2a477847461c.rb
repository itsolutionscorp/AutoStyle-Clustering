class Complement
  @@dna_to_rna_transcriptions = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
  def self.of_dna dna
    transcribe @@dna_to_rna_transcriptions, dna
  end

  def self.of_rna rna
    transcribe @@dna_to_rna_transcriptions.invert, rna
  end

  def self.transcribe transcriptions, nucleotide
    nucleotide.each_char.map{ |char| transcriptions[char] }.join ""
  end
end
