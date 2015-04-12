class Hamming

  def compute(a, b)
    [a,b].min.chars.keep_if.with_index { |x, i| x != [a,b].max.chars[i] }.length
  end
end
