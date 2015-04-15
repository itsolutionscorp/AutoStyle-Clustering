class DNA
  
  def initialize(base)
    @strand = Nucleotide.new base
  end

  def to_rna
    dna?(@strand) ? replace(@strand.base) : @strand.base
  end

  private
  
  def replace(base)
    base.gsub "T", "U"
  end

  def dna?(strand)
    strand.base.include? "T"
  end

end

class Nucleotide

  attr_accessor :base

  def initialize(base)
    @base = base
  end
end
