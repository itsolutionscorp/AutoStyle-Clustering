class DNA
  def initialize(dna_chain)
    @dna = AcidChain.new(dna_chain)
  end

  def to_rna
    @dna.to_rna.to_s
  end
end

class AcidChain
  def initialize(chain)
    @chain = chain.to_s.split("").flat_map { |acid| Acid.new(acid) }
  end

  def to_s
    @chain.join("")
  end

  def inspect
    to_s
  end

  def transform_chain(method)
    AcidChain.new( @chain.map { |acid| acid.send(method) }.join("") )
  end

  def to_rna
    transform_chain(:to_rna)
  end

  def to_dna
    transform_chain(:to_dna)
  end

  class Acid
    def initialize(one_acid)
      @acid = one_acid
    end

    def to_rna
      return Acid.new("U") if @acid == "T"
      self
    end

    def to_dna
      return Acid.new("T") if @acid == "U"
      self
    end

    def valid?
      ["C","G","A","T","U"].include?(@acid)
    end

    def to_s
      @acid.to_s
    end

    def inspect
      to_s
    end

  end
end
