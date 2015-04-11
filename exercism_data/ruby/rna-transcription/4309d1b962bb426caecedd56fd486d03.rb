class DNA
  Adenine = "A"
  Cytosine = "C"
  Guanine = "G"
  Thymine = "T"
  Uracil = "U"
  
  def initialize(sequence) 
    @sequence = sequence
  end

  def to_rna
    @sequence.tr(Thymine, Uracil)
  end
end
