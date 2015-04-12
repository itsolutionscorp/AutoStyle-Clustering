class Hamming
  def compute(strand1, strand2)
    comparison = strand1.chars.zip(strand2.chars)
    comparison.count {|position| position[0] != position[1] if position[1]}
  end
end
