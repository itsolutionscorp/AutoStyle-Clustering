class DeoxyribonucleicAcid < String
  def initialize(dna)
    super
    @dna = dna
  end

  def to_rna
    RibonucleicAcid.new(@dna.gsub('T','U'))
  end
end

class RibonucleicAcid < String
  def initialize(rna)
    super
    @rna = rna
  end
end

# ...okay.
