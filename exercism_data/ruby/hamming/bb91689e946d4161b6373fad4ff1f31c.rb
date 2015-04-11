class Hamming
  def self.compute(base,comp)
     base.length.times.count {|i| base[i] != comp[i] }
  end
end
