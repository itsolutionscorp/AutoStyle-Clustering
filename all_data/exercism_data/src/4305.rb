class Hamming
  def compute(string_a, string_b)
    upper_bound = [string_a.length, string_b.length].min
    (0...upper_bound).count { |n| string_a[n] != string_b[n] }
  end
end
