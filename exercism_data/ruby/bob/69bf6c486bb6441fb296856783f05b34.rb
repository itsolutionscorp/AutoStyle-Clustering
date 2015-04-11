class DNAToRNATranslator
  def translate dna_sequence
    dna_sequence.gsub "T", "U"
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
