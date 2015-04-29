Strand = Struct.new(:code) do
  include Comparable

  def <=> other
    if other.instance_of? self.class
      other.code <=> code
    else
      other <=> code
    end
  end

  def to_str
    code
  end
end

class DNA < Strand
  def to_rna
    DNAToRNAConverter.convert self
  end
end

class RNA < Strand
end

class DNAToRNAConverter
  attr_accessor :dna

  def initialize dna
    self.dna = dna
  end

  def self.convert dna
    self.new(dna).convert
  end

  def convert
    RNA.new rna_code
  end

  def rna_code
    dna_code.tr "T", "U"
  end

  def dna_code
    dna.code
  end
end
