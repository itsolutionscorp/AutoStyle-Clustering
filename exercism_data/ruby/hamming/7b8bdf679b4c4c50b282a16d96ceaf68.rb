class Hamming
  def self.compute(str1, str2)
    score = 0
    str1.size.times { |n| score += 1 unless str1[n] == str2[n] }
    score
  end
end
