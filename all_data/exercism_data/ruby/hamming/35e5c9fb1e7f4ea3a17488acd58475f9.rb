# Hamming class
class Hamming

  def self.compute(s1, s2)
    dist = 0
    dist = compute_strand(s1.split(''), s2.split(''))
    dist
  end

  def self.compute_strand(s1, s2)
    h = 0
    count = 0
    s1.each do |i|
      h += 1 if i != s2[count]
      count += 1
    end
    h
  end
end
