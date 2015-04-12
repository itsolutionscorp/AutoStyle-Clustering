class Hamming
  def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).reduce(0) do |distance, bases|
      distance += (bases[0] <=> bases[1]).abs
    end
  end
end
