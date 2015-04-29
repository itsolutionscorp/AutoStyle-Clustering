class DNAToRNATranslator
  THYMINE = "T"
  URACIL  = "U"

  def translate dna_sequence
    dna_sequence.tr THYMINE, URACIL
  end
end


class DNA
  def initialize dna_sequence
    @dna_sequence = dna_sequence
  end

  def to_rna translator = DNAToRNATranslator.new
    translator.translate(@dna_sequence)
  end
end
