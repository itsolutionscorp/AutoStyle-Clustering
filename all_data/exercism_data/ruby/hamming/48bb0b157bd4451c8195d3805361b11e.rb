class Hamming
  def self.compute(s1, s2)
    diff = 0
    get_shorter_length(s1,s2).times do |i|
      if s1[i] != s2[i]
        diff += 1
      end
    end
    diff
  end
  def self.get_shorter_length s1, s2
    [s1.length, s2.length].min
  end
end
