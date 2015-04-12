class Hamming
  def compute(str1, str2)
    return nil unless str1.size == str2.size
    distance = 0
    str1.size.times { |i| distance += 1 unless str1[i] == str2[i] }
    distance
  end
end
