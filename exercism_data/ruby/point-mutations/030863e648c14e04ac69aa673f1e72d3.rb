class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def hamming_distance(mutant)
    i = [@sequence.length, mutant.length].min
    [@sequence[0..i-1].split(''), mutant[0..i-1].split('')].transpose.count { |x| x[0] != x[1] }
  end
end
