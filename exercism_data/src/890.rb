class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).inject(0) do |sum, i|
      (i[0] != i[1]) ? sum += 1 : sum
    end
  end
end
