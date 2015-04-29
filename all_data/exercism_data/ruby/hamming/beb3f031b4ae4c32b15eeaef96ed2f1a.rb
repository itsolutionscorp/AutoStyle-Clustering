class Hamming

  def self.compute(s, t)
    distance = 0
    t_chars = t.chars.to_a
    s.chars.each_with_index do |el, idx|
      distance += 1 if t_chars[idx] && el != t_chars[idx]
    end
    distance
  end

end
