# Hamming class
class Hamming
  def compute(s1, s2)
    dist = 0
    s1.length.each do |i|
      dist += 1 if s1.split('')[i] != s2.split('')[i]
    end
    dist
  end
end
