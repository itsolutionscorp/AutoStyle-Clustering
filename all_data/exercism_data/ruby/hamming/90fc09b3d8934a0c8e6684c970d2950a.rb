class Hamming
  def self.compute(strand_one, strand_two)
    # Swap strands if necessary since we only care about the shortest, then convert strands to bytes and combine
    pairs = strand_one.size > strand_two.size ? strand_two.bytes.zip(strand_one.bytes) : strand_one.bytes.zip(strand_two.bytes)

    # Count differences
    pairs.count{ |one, two| one != two }
  end
end
