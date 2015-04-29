class Hamming
  def self.compute(s1, s2)
    l = (s1.length > s2.length ? s2.length : s1.length)
    acc = 0
    (0..l-1).each { |n|
      if s1[n] != s2[n]
        acc += 1
      end
    }
    acc
  end
end
