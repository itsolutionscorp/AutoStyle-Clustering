class Hamming
  def self.compute(base, another)
    ([base.size, another.size].min - 1).downto(0).count do |i|
      base[i] != another[i]
    end
  end
end
