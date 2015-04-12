class Hamming
  def compute(a, b)
    distance = 0
    a.split(//).each_with_index do |e, i|
      if a[i] && b[i]
        distance += 1 unless a[i] == b[i]
      end
    end
    return distance
  end
end
