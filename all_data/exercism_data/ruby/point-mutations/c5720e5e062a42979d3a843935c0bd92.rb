class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(sequence)
    zip(@strand, sequence).count{ |pair| mutation?(pair) }
  end

  private

  def zip(strand_0,strand_1)
    strand_0.chars.zip(strand_1.chars)
  end

  def mutation?(pair)
    valid_pair?(pair) && increment?(pair)
  end

  def valid_pair?(pair)
    pair[0] && pair[1]
  end

  def increment?(pair)
    pair[0] != pair[1]
  end

end
