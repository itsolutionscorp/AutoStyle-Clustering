class Complement
  DNA_COMPLEMENT = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_COMPLEMENT = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def self.of_dna(dna_strand)
    NucleotidesTranscribe.transcribe(dna_strand, DNA_COMPLEMENT)
  end

  def self.of_rna(rna_strand)
    NucleotidesTranscribe.transcribe(rna_strand, RNA_COMPLEMENT)
  end
end

private
class NucleotidesTranscribe
  def self.transcribe(nucleotides_strand, nucleotides_complement)
    nucleotides_strand.gsub!(/./, nucleotides_complement)
  end
end
