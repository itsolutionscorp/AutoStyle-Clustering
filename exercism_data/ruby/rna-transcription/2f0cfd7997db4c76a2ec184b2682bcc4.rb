class DNA < String
  attr_reader :strand
  def initialize(strand)
    @strand = strand
  end

  def to_rna
    translate(strand).to_rna
  end

  private
  def translate(strand)
    GeneTranslator.new(strand)
  end
end

class GeneTranslator < String
  def to_rna
    gsub(/T/, 'U')
  end
end
