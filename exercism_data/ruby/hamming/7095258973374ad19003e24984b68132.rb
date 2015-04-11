class Hamming
  def self.compute s1, s2
    gap = 0
    shortest = [s1.length, s2.length].min 
    shortest.times { |i| gap += 1 if s1[i] != s2[i] }
    gap
  end
end
