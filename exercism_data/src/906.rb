class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).map{|arr| arr[0] == arr[1] ? 0 : 1}.inject(:+)
  end
end
