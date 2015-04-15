class DNA

  def initialize sequence
    @sequence = sequence
  end

  def to_rna
    RNA.encode_from_dna @sequence
  end

end

class RNA

  def self.encode_from_dna sequence
    sequence.gsub("T","U")
  end

end
