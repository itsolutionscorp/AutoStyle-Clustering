class Hamming
  def compute(s, t)
    return 0 if s == t
    hamming = 0
    (0...s.length).each do |i|
      hamming += 1 if s[i] != t[i]
    end
    hamming
  end
end
