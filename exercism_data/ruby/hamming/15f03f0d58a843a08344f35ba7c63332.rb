class Hamming
  def self.compute(strand_1, strand_2)
    strand_pairs = merge(strand_1, strand_2)
    strand_pairs.reduce(0){ |total_distance, strand_pair| total_distance + distance(strand_pair) }
  end

  def self.merge(strand_1, strand_2)
    strand_1, strand_2 = strand_2, strand_1 if strand_2.length < strand_1.length
    strand_1.split("").zip(strand_2.split(""))
  end

  def self.swap(strand_1, strand_2)
    strand_1, strand_2 = strand_2, strand_1
  end

  def self.distance(letter_pair)
    letter_pair[0] == letter_pair[1] ? 0 : 1
  end
end
