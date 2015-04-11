class DNA
  CYTIDINE  = "C"
  URIDINE   = "U"
  ADENOSINE = "A"
  GUANOSINE = "G"
  THYMIDINE = "T"

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    @nucleotides.tr(THYMIDINE, URIDINE)
  end
end
