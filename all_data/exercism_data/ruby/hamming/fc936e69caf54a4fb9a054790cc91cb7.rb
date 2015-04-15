class Hamming
  def self.compute(str1, str2)
    return nil unless str1.size == str2.size
    distance = 0
    str1.size.times do |i|
      distance += 1 unless str1[i] == str2[i]
    end
    distance
  end
end
