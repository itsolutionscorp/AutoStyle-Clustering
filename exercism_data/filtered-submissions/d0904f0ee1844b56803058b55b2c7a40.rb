class Hamming
  def compute(strand_1, strand_2)
    strands_in_parallel = strand_1.chars.zip(strand_2.chars)
    different = ->(pair) { pair.inject(:!=) }

    strands_in_parallel.count(&different)
  end
end
