class GeneticCode
  include Comparable
  attr_reader :code

  def initialize code
    @code = code
  end

  def <=> other
    if other.instance_of? self.class
      self.code <=> other.code
    else
      self.code <=> other
    end
  end

  def to_str
    code
  end
end

class DNA < GeneticCode
  def to_rna
    DNAToRNAConverter.convert self
  end
end

class RNA < GeneticCode
end

class DNAToRNAConverter
  def self.convert dna
    rna_code = dna.code.tr "T", "U"
    RNA.new rna_code
  end
end
