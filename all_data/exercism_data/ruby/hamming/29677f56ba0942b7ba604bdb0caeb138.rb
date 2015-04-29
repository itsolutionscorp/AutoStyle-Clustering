class Hamming
  def self.compute(s1, s2)
    valid_length = [s1.length, s2.length].min
    s1 = s1.chars.take(valid_length)
    s2 = s2.chars.take(valid_length)
    s1.zip(s2).count { |c| c[0] != c[1] }
  end
end
