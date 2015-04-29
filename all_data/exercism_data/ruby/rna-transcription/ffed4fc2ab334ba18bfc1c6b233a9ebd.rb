class DNA
  attr_reader :sequence
  def initialize(sequence)
    @sequence = sequence.gsub("U", "T")
  end

  def to_rna
    RNA.new(sequence).to_s
  end

  def to_s
    sequence
  end
end

class RNA
  attr_reader :sequence
  def initialize(sequence)
    @sequence = sequence.gsub("T", "U")
  end

  def to_dna
    DNA.new(sequence).to_s
  end

  def to_s
    sequence
  end
end
