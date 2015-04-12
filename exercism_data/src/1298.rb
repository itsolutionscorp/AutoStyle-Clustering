class Hamming
  def compute(a, b)
    a[0, b.size].chars.zip(b.chars).select { |n| !n[0].eql? n[1] }.count
  end
end
