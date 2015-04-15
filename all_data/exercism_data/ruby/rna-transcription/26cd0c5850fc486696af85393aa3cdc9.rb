class DNA
  def initialize(strand)
    @strand = strand
  end

  def to_rna
    strand.gsub('T', 'U')
  end

  private

  attr_reader :strand
end
