module Hamming
  def self.compute(*args)
    pairs_a, pairs_b = args.sort_by(&:length).map(&:chars)
    pairs_a.zip(pairs_b).count {|(a, b)| a != b }
  end
end