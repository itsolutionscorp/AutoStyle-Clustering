class DNA
  def initialize(dna)
    @dna = dna.to_s.upcase
    @dna = '' if invalid?
  end

  def to_rna
    @dna.gsub('T', 'U')
  end

  def invalid?
    ! @dna =~ /\A[ACGT]+[ACGT]*\Z/
  end
end
