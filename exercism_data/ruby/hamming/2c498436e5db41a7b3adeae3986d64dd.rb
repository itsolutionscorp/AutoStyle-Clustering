class Hamming
  def self.compute(a, b)
    pairs = [a.chars.to_a,b.chars.to_a].sort_by(&:length).reduce(&:zip) # larger zipped into smaller means we always have the right number of pairs.

    pairs.count {|pair_a,pair_b| pair_a != pair_b }
  end
end
