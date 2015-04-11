class Hamming
  def self.compute(a, b)
    # larger zipped into smaller means we always have the right number of pairs.
    pairs = [a.chars,b.chars].sort_by(&:count).reduce(&:zip)

    pairs.count {|character_a,character_b| character_a != character_b }
  end
end
