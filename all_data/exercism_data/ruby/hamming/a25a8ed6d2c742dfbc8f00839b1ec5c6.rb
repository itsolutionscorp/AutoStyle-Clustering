class Hamming
  def self.compute(g1, g2)
    count = 0
    loop = [g1.length, g2.length].min
    loop.times do |i|
      count += 1 if g1[i] != g2[i]
    end
    count
  end
end
