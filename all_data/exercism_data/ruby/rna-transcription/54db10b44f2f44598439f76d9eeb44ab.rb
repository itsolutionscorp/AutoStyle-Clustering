class DNA

  def initialize(strand_string)
    @strand_string = strand_string
  end

  def to_rna
    @strand_string.tr 'T', 'U'
  end

end
