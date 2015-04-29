class Hamming
  def self.compute(first, second)
    # Get the longest string first
    long, short = [first, second].sort { |ary| ary.length }
    # For each pair, get the ones we care about (different and both present)
    pairwise_array(long, short).select do |a, b|
      # `a` is guaranteed to be present as it's from the longer string
      !b.nil? && a != b
    end.size
  end

  def self.pairwise_array(a, b)
    a.split('').zip(b.split(''))
  end
end
