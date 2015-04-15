class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = {
   'C' => 'G',
   'G' => 'C',
   'A' => 'T',
   'U' => 'A'
  }

  def self.of_dna(dna)
    self.translate_strands(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    self.translate_strands(rna, RNA_TO_DNA)
  end

  private

  def self.translate_strands(strand, mapping)
    strand.split(//).map do |transcription|
      mapping[transcription]
    end.join
  end
end
