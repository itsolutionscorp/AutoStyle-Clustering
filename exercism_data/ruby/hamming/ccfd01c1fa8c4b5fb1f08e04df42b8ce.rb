class Hamming 
  def self.compute(s1, s2)
    iterations = [s1.length, s2.length].min

    hd = 0
    iterations.times do |i|
      hd += 1 if s1[i] != s2[i]
    end

    hd
  end
end
