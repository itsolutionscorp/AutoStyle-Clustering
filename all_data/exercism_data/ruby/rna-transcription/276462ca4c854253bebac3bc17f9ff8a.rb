class DNA < Struct.new(:sequence_string)
  def to_rna
    sequence_string.tr(Thymidine.new, Uracil.new)
  end
end

class Thymidine
  def self.new;
    "T"
  end
end

class Uracil
  def self.new
    "U"
  end
end
