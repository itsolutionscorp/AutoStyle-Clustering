class Hamming
  def compute(s1, s2)
    s1.split('').zip(s2.split('')).count { |a, b| a != b }
  end
end
