class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).count {|arr| arr[0] != arr[1]}
  end
end