class Hamming
  def compute(strand_one, strand_two)
    # Convert strands to bytes and combine
    strands = strand_one.bytes.zip(strand_two.bytes)

    # XOR each pair to 1 or 0 and sum
    strands.map{ |a, b| (a || b) ^ (b || a) > 0 ? 1 : 0 }.inject(:+)
  end
end
