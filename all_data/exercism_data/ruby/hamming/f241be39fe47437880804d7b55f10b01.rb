module Hamming
  def hamming(other)
    self.compute(self,other)
  end

  extend self

  def compute(s,t)
    [s,t].map(&:size).min.times.count do |i|
      s[i] != t[i]
    end
  end
end
