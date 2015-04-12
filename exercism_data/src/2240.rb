class Hamming
  def compute(a, b)
    differences = 0
    [a.length, b.length].min.times do |i|
      if a[i] != b[i]
        differences += 1
      end
    end
    differences
  end
end
