class Hamming
  def self.compute(base, desc)
    (0..base.length).count do |i|
      base[i] != desc[i]
    end
  end
end