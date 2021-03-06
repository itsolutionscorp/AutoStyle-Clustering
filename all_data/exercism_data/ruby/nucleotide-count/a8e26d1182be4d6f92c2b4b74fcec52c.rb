class DNA
  attr_reader :sequence

  Adenine = 'A'
  Thymine = 'T'
  Cytosine = 'C'
  Guanine = 'G'
  Uracil = 'U'

  def initialize(sequence)
    raise ArgumentError unless valid_dna?(sequence)
    @sequence = sequence.chars
  end

  def count(nucleotide)
    return nucleotide_counts unless nucleotide
    if nucleotide =~ /^[ATCG]$/
      nucleotide_counts[nucleotide]
    elsif nucleotide == Uracil
      0
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    counts = { Adenine => 0, Thymine => 0, Cytosine => 0, Guanine => 0 }
    counts.keys.each { |base| counts[base] = sequence.count(base) }
    counts
  end

  private


  def valid_dna?(sequence)
    sequence =~ /\A[ATCG]*\z/
  end

end
