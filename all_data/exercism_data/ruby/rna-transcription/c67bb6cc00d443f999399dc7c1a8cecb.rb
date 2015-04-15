class DNA
  ADENINE = "A"
  CYTOSINE = "C"
  GUANINE = "G"
  THYMINE = "T"
  URACIL = "U"

  RNA_MAPPING = {
    THYMINE => URACIL
  }

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    @nucleotides.gsub(/[#{RNA_MAPPING.keys.join}]/, RNA_MAPPING)
  end
end
