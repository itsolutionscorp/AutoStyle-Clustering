class DNA < Struct.new(:sequence_string)
  def to_rna
    transcribe
  end

  private

  def transcribe
    sequence_string.tr Thymidine.indicator, Uracil.indicator
  end
end

class Thymidine
  def self.indicator
    "T"
  end
end

class Uracil
  def self.indicator
    "U"
  end
end
