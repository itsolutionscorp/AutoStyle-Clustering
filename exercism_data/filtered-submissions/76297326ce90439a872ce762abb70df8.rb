class Hamming
  def compute(a, b)
    return 0 unless a && b && a != b

    length = [a.length, b.length].min

    return 0 if length == 0

    distance = 0

    (0..length-1).each do |i|
      distance += 1 if a[i] != b[i]
    end

    distance
  end
end
