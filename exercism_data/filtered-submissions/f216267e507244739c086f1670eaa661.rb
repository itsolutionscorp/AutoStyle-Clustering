class Hamming
  def compute(a, b)
    return nil unless a.length == b.length

    pairs = a.split('').zip(b.split(''))
    differences = pairs.select {|pair| pair[0] != pair[1]}
    differences.length
  end
end
