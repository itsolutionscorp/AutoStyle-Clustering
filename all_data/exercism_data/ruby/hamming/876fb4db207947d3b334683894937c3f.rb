module Hamming
  def self.compute(a, b)
	  a.split("").zip(b.split("")).each.count { |a, b| count+= 1 unless a.eql?(b) }
  end
end
