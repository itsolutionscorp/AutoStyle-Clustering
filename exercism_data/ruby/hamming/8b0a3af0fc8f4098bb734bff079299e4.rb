class Hamming
  def self.compute(source, target)
    nucleotide_pairs = source.chars.zip(target.chars)
    nucleotide_pairs.reduce(0){|sum, nucleotide_pair| sum + distance_for_nucleotide_pair(nucleotide_pair)}
  end

  private
  def self.distance_for_nucleotide_pair(pair)
    pair[0] == pair[1] || pair[1].nil? ? 0 : 1
  end
end
