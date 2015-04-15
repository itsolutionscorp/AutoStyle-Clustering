module Hamming

  def self.compute (sample, target)
    total = 0
    s = sample.unpack("c*")
    t = target.unpack("c*")
    s.zip(t).each do |a, b|
      total+= 1 if a!= b && a && b 
    end
    total
  end
end
