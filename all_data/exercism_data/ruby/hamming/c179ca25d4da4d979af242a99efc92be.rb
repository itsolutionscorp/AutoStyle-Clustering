class Hamming
  def self.compute(string_a, string_b)
    distance = 0
    for i in 0..(string_a.size - 1)
      distance += 1 if string_a[i] != string_b[i]
    end
    distance
  end
end
