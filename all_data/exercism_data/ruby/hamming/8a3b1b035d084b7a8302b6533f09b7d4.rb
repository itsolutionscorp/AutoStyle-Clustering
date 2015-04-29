module Hamming
  extend self

  def compute(a, b)
    count_differences_in valid_pairs a.chars.zip(b.chars)
  end

  def count_differences_in(pairs)
    pairs.count {|(left,right)| left != right }
  end

  def valid_pairs(pairs)
    pairs.reject {|pair| any_nil? pair }
  end

  def any_nil?(pair)
    pair.any? {|element| element.nil? }
  end

end
