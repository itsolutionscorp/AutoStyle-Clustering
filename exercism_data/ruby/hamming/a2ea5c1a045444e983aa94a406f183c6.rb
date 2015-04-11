class Hamming
  def self.compute(s,t)
    return self.compute(t, s) if t.length < s.length
    self.compute_enum(s.chars.zip t.chars)
  end

  private

  def self.compute_enum(zip)
    zip.count { |pair| pair[0] != pair[1] }
  end
end
