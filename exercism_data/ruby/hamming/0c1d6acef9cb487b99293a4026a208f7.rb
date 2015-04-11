module Hamming
  extend self

  def compute(strand_one, strand_two)
    # Get the shorter strand size and iterate with it while counting differences
    # in the bases using the cycle number as index.
    [strand_one.size, strand_two.size].min.times.count do |i|
      strand_one[i] != strand_two[i]
    end
  end
end
