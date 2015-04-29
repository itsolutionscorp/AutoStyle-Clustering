class Hamming
  def self.compute(base, other)
    res = 0
    base.length.times do |i|
      res += 1 unless base[i] == other[i]
    end
    res
  end
end

#p Hamming.compute('GATACA', 'GCATAA')
