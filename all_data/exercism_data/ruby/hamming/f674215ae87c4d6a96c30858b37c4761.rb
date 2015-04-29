class Hamming
  def self.compute(base,comp)
    base.chars.zip(comp.chars).count{|a,b| a != b}
  end
end
