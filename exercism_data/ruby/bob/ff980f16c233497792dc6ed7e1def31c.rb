class DNA
  attr_reader :seq
  def initialize(seq)
    @seq = seq
  end

  def to_rna
    seq.gsub('T','U')
  end
end
