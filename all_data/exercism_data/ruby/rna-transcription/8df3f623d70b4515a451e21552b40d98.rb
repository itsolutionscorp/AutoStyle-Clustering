class DNA
  def initialize(dna)
    @dna = dna
  end

  def to_rna
    @rna ||= DNAtoRNA.new(dna).rna
  end

  def dna
    @dna
  end
end

class DNAtoRNA
  def initialize(dna)
    @dna = dna
  end

  def rna
    @rna ||= RNA.new(converted_dna).rna
  end

  def dna
    @dna
  end

protected
  def converted_dna
    dna.gsub('T', 'U')
  end
end

class RNA
  def initialize(rna)
    @rna = rna
  end

  def rna
    @rna
  end
end
