class RNA
  URACIL = "U"

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  # This would work great if MRI's String#== would actually call .to_str on other
  def to_str
    @nucleotides.dup
  end
end

class DNA
  ADENINE = "A"
  CYTOSINE = "C"
  GUANINE = "G"
  THYMINE = "T"

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
   nucleotides_to_rna_nucleotides
  end

  private

  def nucleotides_to_rna_nucleotides
    @nucleotides.tr(THYMINE, RNA::URACIL)
  end
end
