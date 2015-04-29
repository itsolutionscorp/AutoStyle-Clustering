class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(mutant)
    @sequence.chars.zip(mutant.chars).count do |x|
      !x.include?(nil) && x[0] != x[1]
    end
  end
end
