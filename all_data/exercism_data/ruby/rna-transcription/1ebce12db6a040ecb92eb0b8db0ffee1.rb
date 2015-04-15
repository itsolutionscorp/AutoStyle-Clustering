class DNA
  THYMIDINE = 'T'
  URACIL = 'U'

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    strand.gsub(THYMIDINE, URACIL)
  end

  private

  attr_reader :strand
end
