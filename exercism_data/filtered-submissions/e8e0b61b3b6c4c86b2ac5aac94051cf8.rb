class Hamming
  def compute(a, b)
    a = a.chars
    b = b.chars

    a.zip(b).reduce(0) do |distance, (a, b)|
      distance += (a <=> b).abs
    end
  end
end
