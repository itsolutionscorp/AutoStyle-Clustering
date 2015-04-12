class Hamming
  def compute(s1, s2)
    raise ArgumentError if s1.length != s2.length

    s1.split('').zip(s2.split('')).reject{|s1, s2| s1 == s2}.length
  end
end
