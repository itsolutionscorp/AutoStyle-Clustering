class Hamming

  def compute(a, b)
    (0..a.length).count { |n| a[n] != b[n] }
  end

end
