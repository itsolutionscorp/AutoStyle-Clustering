class Hamming
  def self.compute(first, second)
    first_strand_adjusted_to_min_length = first.chars.take(second.chars.length)
    first_strand_adjusted_to_min_length.zip(second.chars)
                  .reject {|points| points.first == points.last}
                  .length
  end
end
