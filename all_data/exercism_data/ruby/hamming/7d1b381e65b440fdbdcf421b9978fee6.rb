class Hamming
  def self.compute(s, t)
      (0...s.length).count { |i| !(s[i].eql?(t[i])) }
  end
end