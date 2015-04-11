class Hamming
  def self.compute(derp, flerp)
    derp.chars.zip(flerp.chars).select do |a,b|
      a != b
    end.count
  end
end
