class DNA < Struct.new(:dna_string)
  def to_rna
    RNA.new(transcribe).rna_string
  end

  def transcribe
    dna_string.tr(Thymidine.new, Uracil.new)
  end

  def cell_division
    DNA.new(dna_string)
  end
end

class RNA < Struct.new(:rna_string)
  def to_protein
    synthesize
  end

  def synthesize
    Protein.new self
  end
end

class Protein; end

class Thymidine < String
  def initialize
    super "T"
  end
end

class Uracil < String
  def initialize
    super "U"
  end
end
