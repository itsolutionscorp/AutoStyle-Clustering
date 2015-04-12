module Hamming
  extend self

  def compute(group_one, group_two)
    # Get the shorter group size and iterate with it while counting differences
    # in the elements of the same position using the cycle number as index.
    [group_one.size, group_two.size].min.times.count do |i|
      group_one[i] != group_two[i]
    end
  end
end
