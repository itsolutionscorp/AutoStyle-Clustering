class Hamming
  def self.compute(s,t)
    self.compute_array(s.chars.to_a, t.chars.to_a)
  end

  private

  def self.compute_array(a, b)
    return 0 if a.empty? or b.empty?
    (a.first == b.first ? 0 : 1) + compute_array(a.drop(1), b.drop(1))
  end
end
