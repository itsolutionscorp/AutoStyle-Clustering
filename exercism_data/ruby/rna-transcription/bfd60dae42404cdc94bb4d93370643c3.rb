class DNA < Struct.new(:dna_string)
  def to_rna
    dna_string.tr(Thymidine.new, Uracil.new)
  end
end

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
