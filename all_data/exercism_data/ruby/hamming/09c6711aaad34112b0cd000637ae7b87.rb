module Hamming
  def self.compute(as, bs)
    as.chars.zip(bs.chars).select {|a,b| a && b and a != b}.count
  end
end
