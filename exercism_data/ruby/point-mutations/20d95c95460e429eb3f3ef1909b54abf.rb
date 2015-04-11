class DNA

  def initialize(nucleotides)
    @nucleotides = nucleotides.chars
  end

  def hamming_distance(sequence)
    pairs = @nucleotides.first(sequence.length).zip(sequence.chars)
    pairs.count {|this, that| this != that }
  end
end
