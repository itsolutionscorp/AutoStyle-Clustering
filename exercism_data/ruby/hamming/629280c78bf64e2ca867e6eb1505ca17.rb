class Hamming
  def self.compute(first, second)
    zipped_point_pairs = first[0..second.length - 1].chars.zip(second.chars)
    zipped_point_pairs.count {|first_point, second_point| first_point != second_point }
  end
end