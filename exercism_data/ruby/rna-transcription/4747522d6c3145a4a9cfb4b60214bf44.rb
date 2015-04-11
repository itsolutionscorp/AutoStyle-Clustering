class DNA
  def initialize(dna)
    @dna = dna
  end

  def to_rna
    RNA.from_dna(@dna)
  end
end

class RNA

  DNA_MATCHER = /t/i
  RNA_TRANSCRIPTION = 'u'

  def self.from_dna(dna)
    dna.gsub(DNA_MATCHER, RNA_TRANSCRIPTION).upcase
  end
end
