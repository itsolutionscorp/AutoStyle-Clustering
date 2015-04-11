class Hamming
  def self.compute(str1, str2)
    score = 0
    for i in 0...str1.size
      if str1[i] == str2[i]
        score += 0
      else
        score += 1
      end
    end
    score
  end
end
