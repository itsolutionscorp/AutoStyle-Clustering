class Hamming
  def compute(s1, s2)
    paired = s1.chars.zip(s2.chars)
    paired.count do |p1, p2|
      p1 != p2
    end
  end
end
