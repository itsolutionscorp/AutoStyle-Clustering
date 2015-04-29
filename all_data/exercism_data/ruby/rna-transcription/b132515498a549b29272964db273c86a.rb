class DNA
  def initialize(dna)
    @dna = dna
  end

  def to_rna
    if %w[C G A].include? @dna
      @dna
    else
      'U'
    end
  end
end
