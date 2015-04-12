class Hamming
  def compute(strand1, strand2)
    a = strand1.chars
    b = strand2.chars

    max = [a.count, b.count].min
    a = a[0, max]
    b = b[0, max]

    y = a.zip(b)
    y.inject(0) { |sum, comparison_array| sum += 1 if comparison_array.first != comparison_array.last; sum }
  end

end
