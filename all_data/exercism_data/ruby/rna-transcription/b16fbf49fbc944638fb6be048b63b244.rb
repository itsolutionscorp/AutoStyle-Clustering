class DNA
  attr_reader :strand
  def initialize(strand = '')
    @strand = strand.upcase
  end

  def to_rna
    thymine_to_uracil
  end

  private

  def thymine_to_uracil
    strand.gsub('T', 'U')
  end
end
