class Hamming
  def self.compute(first, second)
    # Get the longest string
    short, long = [first, second].sort_by(&:length)
    # For each pair, get the ones we care about (different and both present)
    pairwise_array(long, short).count do |a, b|
      # `a` is guaranteed to be present as it's from the longer string
      !b.nil? && a != b
    end
  end

  def self.pairwise_array(a, b)
    a.chars.zip(b.chars)
  end
end
