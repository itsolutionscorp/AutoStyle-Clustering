module Hamming
  
  def hamming(other)
    Hamming.compute(self,other)
  end

  def -(other)
	  hamming other
  end

  module_function
  def compute(s,t)
	  [s,t].map(&:length).min.times.count do |i|
		  s[i] != t[i]
	  end
  end
end
