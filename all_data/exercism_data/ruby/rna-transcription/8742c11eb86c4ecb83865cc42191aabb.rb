class DNA
  def initialize(dna)
    @dna = dna.to_s.upcase
  end

  def to_rna
    if is_valid?
      @dna.gsub('T', 'U')
    else
      puts 'Your DNA strand is invalid!'
    end
  end

  def is_valid?
    (@dna =~ /\A[ACGT]+[ACGT]*\Z/) > -1
  end
end
