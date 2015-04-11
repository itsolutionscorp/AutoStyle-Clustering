class Hamming
  def self.compute(s1,s2)
    score = 0
    run_length(s1,s2).times do |i|
      score += 1 if s1[i] != s2[i]
    end
    return score
  end

  private

  def self.run_length (s1,s2)
    if s1.size > s2.size
      s2.size
    else
      s1.size
    end
  end
end
