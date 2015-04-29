class Complement

  DNA_HASH = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
  RNA_HASH = DNA_HASH.invert

  def self.of_dna(dna)
    transcribe(dna, DNA_HASH)
  end

  def self.of_rna(rna)
    transcribe(rna, RNA_HASH)
  end

  def self.transcribe(strand, transcription_hash)
    strand.chars.map { |n| transcription_hash[n] }.join
  end
end
