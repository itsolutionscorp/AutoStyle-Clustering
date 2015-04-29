class Hamming
  def self.compute(base, desc)
    base.length.times.count do |i|
      base[i] != desc[i]
    end
  end
end
