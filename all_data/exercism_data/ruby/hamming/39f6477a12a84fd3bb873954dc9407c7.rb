class Hamming
  def self.compute(source, target)
    source = source[0, target.size]
    nucleotide_pairs = source.chars.zip(target.chars)
    nucleotide_pairs.reduce(0){|sum, nucleotide_pair| sum + distance_for_nucleotide_pair(nucleotide_pair)}
  end

  private
  def self.distance_for_nucleotide_pair(pair)
    pair[0] == pair[1] ? 0 : 1
  end
end
