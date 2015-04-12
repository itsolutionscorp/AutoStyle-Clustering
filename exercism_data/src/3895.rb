class Hamming
  def compute(a, b)
    distance = 0

    # Thanks deanlxvii!
    [a.length, b.length].min.times do |i|
      distance += 1 if a[i] != b[i]
    end

    distance
  end
end
