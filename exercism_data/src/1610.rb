class Hamming
  def compute(a, b)
    ary_a = a.chars
    ary_b = b.chars

    ary_a_cropped = ary_a.take(ary_b.length)
    ary_b_cropped = ary_b.take(ary_a.length)

    pairs = ary_a_cropped.zip(ary_b_cropped)
    pairs.count { |x, y| x != y }
  end
end
