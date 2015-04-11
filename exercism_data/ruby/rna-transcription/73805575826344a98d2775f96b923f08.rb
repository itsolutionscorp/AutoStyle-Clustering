class Complement
  def self.of_dna(dna_strand)
    transcription = DnaRnaTranslator.new(dna_strand)
    transcription.translate_dna_to_rna
  end

  def self.of_rna(rna_strand)
    transcription = DnaRnaTranslator.new(rna_strand)
    transcription.translate_rna_to_dna
  end
end

class DnaRnaTranslator
  def initialize(strand)
    @strand = strand.downcase
  end

  def translate_dna_to_rna
    rna_strand = @strand.gsub('g', 'C').gsub('c', 'G').gsub('t', 'A').gsub('a', 'U')
  end

  def translate_rna_to_dna
    dna_strand = @strand.gsub('c', 'G').gsub('g', 'C').gsub('a', 'T').gsub('u', 'A')
  end
end
