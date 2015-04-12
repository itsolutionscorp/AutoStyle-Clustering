class Hamming
  def compute(s1, s2)
    if s1.size > s2.size
      s1, s2 = s2, s1
    end

    n = 0
    s1.size.times do |i|
      n+=1 unless s1[i] == s2[i]
    end

    return n
  end
end
