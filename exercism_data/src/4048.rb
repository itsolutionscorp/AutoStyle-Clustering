class Hamming
  def compute(s, t)
      (0...s.length).count { |i| s[i] != t[i] }
  end
end
