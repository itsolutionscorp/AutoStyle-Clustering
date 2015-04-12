class Hamming
  def compute(a, b)
    a.chars.map.with_index {|_, i| a[i] === b[i] }.count(false)
  end
end
