class Hamming
  def compute s1, s2
    gap = 0
    proper_number = [s1.length, s2.length].max - (s1.length - s2.length).abs
    proper_number.times do |i|
      gap += 1 if s1[i] != s2[i]
    end
    gap
  end
end
