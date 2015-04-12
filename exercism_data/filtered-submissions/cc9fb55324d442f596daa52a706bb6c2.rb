class Hamming
  def compute(s1, s2)
    diff = 0
    [s1.length, s2.length].min.times do |i|
      if s1[i] != s2[i]
        diff += 1
      end
    end
    diff
  end
end
