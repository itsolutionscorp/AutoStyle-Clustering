# Hamming class
class Hamming
  def compute(s1, s2)
    dist = 0
    count = 0
    s1.split('').each do |i|
      dist += 1 if i != s2.split('')[count]
      count += 1
    end
    dist
  end
end
