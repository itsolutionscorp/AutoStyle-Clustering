class Hamming
  def compute(a, b)
    a.chars.with_index.count do |char, index|
      (b[index] || char) != char
    end
  end
end
