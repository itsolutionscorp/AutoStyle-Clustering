class Complement
  TRANSCRIPTION_KEY = ['GCTA','CGAU']

  def self.of_dna(strand)
    strand.tr(*TRANSCRIPTION_KEY)
  end

  def self.of_rna(strand)
    strand.tr(*TRANSCRIPTION_KEY.reverse)
  end

end
