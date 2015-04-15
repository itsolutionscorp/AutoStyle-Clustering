# Hamming class
class Hamming

  def self.compute(s1, s2)
    dist = 0
    if s1.length > 1
      dist = multiple_strand(s1.split(''), s2.split(''))
    else
      dist = single_strand(s1, s2)
    end
    dist
  end

  def self.single_strand(s1, s2)
    h = 0
    if s1 == s2
      h == 0
    elsif s1 != s2
      h = 1
    end
    h
  end

  def self.multiple_strand(s1, s2)
    h = 0
    count = 0
    s1.each do |i|
      h += 1 if i != s2[count]
      count += 1
    end
    h
  end
end
