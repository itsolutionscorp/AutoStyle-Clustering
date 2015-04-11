class DNA
  def initialize(dna)
    @dna = dna
  end

  def to_rna
    RNA.from_dna(@dna)
  end
end

class RNA
  def self.from_dna(dna)
    dna.gsub(/t/i, 'u').upcase
  end
end
