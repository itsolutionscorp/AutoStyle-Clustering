class Hamming
  def self.compute(str1, str2)
    score = 0
    (0.upto(str1.length)).each do |i| if str1[i] != str2[i] then score += 1 end end
    score
  end
end
