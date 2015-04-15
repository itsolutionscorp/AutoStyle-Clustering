class DNA
  ADENINE = "A"
  CYTOSINE = "C"
  GUANINE = "G"
  THYMINE = "T"
  URACIL = "U"

  TO_RNA_MAPPING = {
    THYMINE => URACIL
  }

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    @nucleotides.gsub(nucleotides_to_map, TO_RNA_MAPPING)
  end

  private

  def nucleotides_to_map
    /[#{TO_RNA_MAPPING.keys.join}]/
  end
end
